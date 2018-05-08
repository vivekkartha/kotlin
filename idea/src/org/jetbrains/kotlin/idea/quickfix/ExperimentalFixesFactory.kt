/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.quickfix

import com.intellij.codeInsight.intention.IntentionAction
import org.jetbrains.kotlin.cfg.pseudocode.containingDeclarationForPseudocode
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1
import org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2
import org.jetbrains.kotlin.idea.caches.resolve.analyze
import org.jetbrains.kotlin.idea.core.toDescriptor
import org.jetbrains.kotlin.idea.util.projectStructure.module
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.psi.psiUtil.getStrictParentOfType
import org.jetbrains.kotlin.resolve.AnnotationChecker
import org.jetbrains.kotlin.resolve.BindingContext.FQNAME_TO_CLASS_DESCRIPTOR

object ExperimentalFixesFactory : KotlinIntentionActionsFactory() {
    private val USE_EXPERIMENTAL_FQ_NAME = FqName("kotlin.UseExperimental")

    override fun doCreateActions(diagnostic: Diagnostic): List<IntentionAction> {
        val element = diagnostic.psiElement
        val containingDeclaration: KtDeclaration = when (element) {
            is KtElement -> element.containingDeclarationForPseudocode
            else -> element.getStrictParentOfType()
        } ?: return emptyList()

        val annotationFqName = when (diagnostic) {
            is DiagnosticWithParameters1<*, *> -> diagnostic.a as? FqName
            is DiagnosticWithParameters2<*, *, *> -> diagnostic.a as? FqName
            else -> null
        } ?: return emptyList()

        val context = when (element) {
            is KtElement -> element.analyze()
            else -> containingDeclaration.analyze()
        }
        val annotationClassDescriptor = context[FQNAME_TO_CLASS_DESCRIPTOR, annotationFqName.toUnsafe()] ?: return emptyList()
        val applicableTargets = AnnotationChecker.applicableTargetSet(annotationClassDescriptor) ?: KotlinTarget.DEFAULT_TARGET_SET

        fun isApplicableTo(declaration: KtDeclaration, applicableTargets: Set<KotlinTarget>): Boolean {
            val actualTargetList = AnnotationChecker.getDeclarationSiteActualTargetList(
                declaration, declaration.toDescriptor() as? ClassDescriptor, context
            )
            return actualTargetList.any { it in applicableTargets }
        }

        val result = mutableListOf<IntentionAction>()
        val useExperimentalArgs = "$annotationFqName::class"
        run {
            val suffix = " to '${containingDeclaration.name}'"
            if (isApplicableTo(containingDeclaration, applicableTargets)) {
                result.add(AddAnnotationFix(containingDeclaration, annotationFqName, suffix))
            }
            result.add(AddAnnotationFix(containingDeclaration, USE_EXPERIMENTAL_FQ_NAME, suffix, useExperimentalArgs))
        }
        if (containingDeclaration is KtCallableDeclaration) {
            val containingClassOrObject = containingDeclaration.containingClassOrObject
            if (containingClassOrObject != null) {
                val suffix = " to containing class '${containingClassOrObject.name}'"
                if (isApplicableTo(containingClassOrObject, applicableTargets)) {
                    result.add(AddAnnotationFix(containingClassOrObject, annotationFqName, suffix))
                } else {
                    result.add(AddAnnotationFix(containingClassOrObject, USE_EXPERIMENTAL_FQ_NAME, suffix, useExperimentalArgs))
                }
            }
        }
        val containingFile = containingDeclaration.containingKtFile
        val module = containingFile.module
        if (module != null) {
            result.add(
                MakeModuleExperimentalFix(containingFile, module, annotationFqName)
            )
        }

        return result
    }
}