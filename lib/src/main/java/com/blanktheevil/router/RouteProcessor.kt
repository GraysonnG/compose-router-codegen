package com.blanktheevil.router

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.validate
import kotlin.reflect.KClass

class RouteProcessor(
    private val environment: SymbolProcessorEnvironment
): SymbolProcessor {

    private fun Resolver.findAnnotations(
        kClass: KClass<*>,
    ) = getSymbolsWithAnnotation(
        kClass.qualifiedName.toString()
    ).filterIsInstance<KSFunctionDeclaration>()

    private fun createFile(listedFunctions: Sequence<KSFunctionDeclaration>) {
        fun StringBuilder.newLine(numOfLines: Int = 1) {
            for (i in 1..numOfLines.coerceAtLeast(1)) {
                append("\n")
            }
        }

        val sourceFiles = listedFunctions.mapNotNull { it.containingFile }
        val imports = listedFunctions.mapNotNull { "import ${it.qualifiedName?.asString()}" }
        val functionNames = listedFunctions.mapNotNull { it.simpleName.asString() }
        val composables = listedFunctions.map {
            "\"${it.annotations.first { it.shortName.asString() == ComposableRoute::class.simpleName }.arguments.first().value.toString()}\" to { ${it.simpleName.asString()}(navHostController) }"
        }

        val text = buildString {
            append("package com.blanktheevil.router\n")
            append("import androidx.compose.runtime.Composable\n")
            append("import androidx.navigation.NavHostController\n")
            append("import androidx.compose.ui.Modifier\n")
            append("import androidx.navigation.compose.NavHost")
            newLine(2)
            append(imports.joinToString("\n"))
            newLine(2)

            append("fun Routes(navHostController: NavHostController) = mapOf<String, @Composable () -> Unit>(\n\t${composables.joinToString(",\n\t")}\n)")
        }

        val file = environment.codeGenerator.createNewFile(
            Dependencies(
                false,
                *sourceFiles.toList().toTypedArray()
            ),
            "com.blanktheevil.router",
            "Routes"
        )

        file.write(text.toByteArray())
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val routeFunctions: Sequence<KSFunctionDeclaration> =
            resolver.findAnnotations(ComposableRoute::class)
        if (!routeFunctions.iterator().hasNext()) return emptyList()

        createFile(listedFunctions = routeFunctions)

        return (routeFunctions).filterNot { it.validate() }.toList()
    }
}