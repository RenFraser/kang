package org.kang

import org.eclipse.lsp4j.CallHierarchyIncomingCall
import org.eclipse.lsp4j.CallHierarchyIncomingCallsParams
import org.eclipse.lsp4j.CallHierarchyItem
import org.eclipse.lsp4j.CallHierarchyOutgoingCall
import org.eclipse.lsp4j.CallHierarchyOutgoingCallsParams
import org.eclipse.lsp4j.CallHierarchyPrepareParams
import org.eclipse.lsp4j.CodeAction
import org.eclipse.lsp4j.CodeActionParams
import org.eclipse.lsp4j.CodeLens
import org.eclipse.lsp4j.CodeLensParams
import org.eclipse.lsp4j.ColorInformation
import org.eclipse.lsp4j.ColorPresentation
import org.eclipse.lsp4j.ColorPresentationParams
import org.eclipse.lsp4j.Command
import org.eclipse.lsp4j.CompletionItem
import org.eclipse.lsp4j.CompletionList
import org.eclipse.lsp4j.CompletionParams
import org.eclipse.lsp4j.DeclarationParams
import org.eclipse.lsp4j.DefinitionParams
import org.eclipse.lsp4j.DidChangeTextDocumentParams
import org.eclipse.lsp4j.DidCloseTextDocumentParams
import org.eclipse.lsp4j.DidOpenTextDocumentParams
import org.eclipse.lsp4j.DidSaveTextDocumentParams
import org.eclipse.lsp4j.DocumentColorParams
import org.eclipse.lsp4j.DocumentDiagnosticParams
import org.eclipse.lsp4j.DocumentDiagnosticReport
import org.eclipse.lsp4j.DocumentFormattingParams
import org.eclipse.lsp4j.DocumentHighlight
import org.eclipse.lsp4j.DocumentHighlightParams
import org.eclipse.lsp4j.DocumentLink
import org.eclipse.lsp4j.DocumentLinkParams
import org.eclipse.lsp4j.DocumentOnTypeFormattingParams
import org.eclipse.lsp4j.DocumentRangeFormattingParams
import org.eclipse.lsp4j.DocumentSymbol
import org.eclipse.lsp4j.DocumentSymbolParams
import org.eclipse.lsp4j.FoldingRange
import org.eclipse.lsp4j.FoldingRangeRequestParams
import org.eclipse.lsp4j.Hover
import org.eclipse.lsp4j.HoverParams
import org.eclipse.lsp4j.ImplementationParams
import org.eclipse.lsp4j.InlayHint
import org.eclipse.lsp4j.InlayHintParams
import org.eclipse.lsp4j.InlineValue
import org.eclipse.lsp4j.InlineValueParams
import org.eclipse.lsp4j.LinkedEditingRangeParams
import org.eclipse.lsp4j.LinkedEditingRanges
import org.eclipse.lsp4j.Location
import org.eclipse.lsp4j.LocationLink
import org.eclipse.lsp4j.MarkupContent
import org.eclipse.lsp4j.MarkupKind
import org.eclipse.lsp4j.Moniker
import org.eclipse.lsp4j.MonikerParams
import org.eclipse.lsp4j.PrepareRenameDefaultBehavior
import org.eclipse.lsp4j.PrepareRenameParams
import org.eclipse.lsp4j.PrepareRenameResult
import org.eclipse.lsp4j.Range
import org.eclipse.lsp4j.ReferenceParams
import org.eclipse.lsp4j.RenameParams
import org.eclipse.lsp4j.SelectionRange
import org.eclipse.lsp4j.SelectionRangeParams
import org.eclipse.lsp4j.SemanticTokens
import org.eclipse.lsp4j.SemanticTokensDelta
import org.eclipse.lsp4j.SemanticTokensDeltaParams
import org.eclipse.lsp4j.SemanticTokensParams
import org.eclipse.lsp4j.SemanticTokensRangeParams
import org.eclipse.lsp4j.SignatureHelp
import org.eclipse.lsp4j.SignatureHelpParams
import org.eclipse.lsp4j.SymbolInformation
import org.eclipse.lsp4j.TextEdit
import org.eclipse.lsp4j.TypeDefinitionParams
import org.eclipse.lsp4j.TypeHierarchyItem
import org.eclipse.lsp4j.TypeHierarchyPrepareParams
import org.eclipse.lsp4j.TypeHierarchySubtypesParams
import org.eclipse.lsp4j.TypeHierarchySupertypesParams
import org.eclipse.lsp4j.WillSaveTextDocumentParams
import org.eclipse.lsp4j.WorkspaceEdit
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.jsonrpc.messages.Either3
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.TextDocumentService
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

@Suppress("TooManyFunctions")
class KotlinTextDocumentService : TextDocumentService, LanguageClientAware {
    override fun didOpen(p0: DidOpenTextDocumentParams?) {
        LOGGER.info("opened text document")
    }

    override fun didChange(p0: DidChangeTextDocumentParams?) {
        LOGGER.info("changed text document")
    }

    override fun completion(position: CompletionParams?):
        CompletableFuture<Either<MutableList<CompletionItem>, CompletionList>> {
        return super.completion(position)
    }

    override fun resolveCompletionItem(unresolved: CompletionItem?): CompletableFuture<CompletionItem> {
        return super.resolveCompletionItem(unresolved)
    }

    override fun hover(params: HoverParams?): CompletableFuture<Hover> {
        val content = MarkupContent().apply {
            kind = MarkupKind.PLAINTEXT
            value = "test!"
        }

        val hover = Hover(content)
        return CompletableFuture.supplyAsync { hover }
    }

    override fun signatureHelp(params: SignatureHelpParams?): CompletableFuture<SignatureHelp> {
        return super.signatureHelp(params)
    }

    override fun declaration(params: DeclarationParams?):
        CompletableFuture<Either<MutableList<out Location>, MutableList<out LocationLink>>> {
        return super.declaration(params)
    }

    override fun definition(params: DefinitionParams?):
        CompletableFuture<Either<MutableList<out Location>, MutableList<out LocationLink>>> {
        return super.definition(params)
    }

    override fun typeDefinition(params: TypeDefinitionParams?):
        CompletableFuture<Either<MutableList<out Location>, MutableList<out LocationLink>>> {
        return super.typeDefinition(params)
    }

    override fun implementation(params: ImplementationParams?):
        CompletableFuture<Either<MutableList<out Location>, MutableList<out LocationLink>>> {
        return super.implementation(params)
    }

    override fun references(params: ReferenceParams?): CompletableFuture<MutableList<out Location>> {
        return super.references(params)
    }

    override fun documentHighlight(params: DocumentHighlightParams?):
        CompletableFuture<MutableList<out DocumentHighlight>> {
        return super.documentHighlight(params)
    }

    override fun documentSymbol(params: DocumentSymbolParams?):
        CompletableFuture<MutableList<Either<SymbolInformation, DocumentSymbol>>> {
        return super.documentSymbol(params)
    }

    override fun codeAction(params: CodeActionParams?): CompletableFuture<MutableList<Either<Command, CodeAction>>> {
        return super.codeAction(params)
    }

    override fun resolveCodeAction(unresolved: CodeAction?): CompletableFuture<CodeAction> {
        return super.resolveCodeAction(unresolved)
    }

    override fun codeLens(params: CodeLensParams?): CompletableFuture<MutableList<out CodeLens>> {
        return super.codeLens(params)
    }

    override fun resolveCodeLens(unresolved: CodeLens?): CompletableFuture<CodeLens> {
        return super.resolveCodeLens(unresolved)
    }

    override fun formatting(params: DocumentFormattingParams?): CompletableFuture<MutableList<out TextEdit>> {
        return super.formatting(params)
    }

    override fun rangeFormatting(params: DocumentRangeFormattingParams?):
        CompletableFuture<MutableList<out TextEdit>> {
        return super.rangeFormatting(params)
    }

    override fun onTypeFormatting(params: DocumentOnTypeFormattingParams?):
        CompletableFuture<MutableList<out TextEdit>> {
        return super.onTypeFormatting(params)
    }

    override fun rename(params: RenameParams?): CompletableFuture<WorkspaceEdit> {
        return super.rename(params)
    }

    override fun linkedEditingRange(params: LinkedEditingRangeParams?): CompletableFuture<LinkedEditingRanges> {
        return super.linkedEditingRange(params)
    }

    override fun willSave(params: WillSaveTextDocumentParams?) {
        super.willSave(params)
    }

    override fun willSaveWaitUntil(params: WillSaveTextDocumentParams?): CompletableFuture<MutableList<TextEdit>> {
        return super.willSaveWaitUntil(params)
    }

    override fun documentLink(params: DocumentLinkParams?): CompletableFuture<MutableList<DocumentLink>> {
        return super.documentLink(params)
    }

    override fun documentLinkResolve(params: DocumentLink?): CompletableFuture<DocumentLink> {
        return super.documentLinkResolve(params)
    }

    override fun documentColor(params: DocumentColorParams?): CompletableFuture<MutableList<ColorInformation>> {
        return super.documentColor(params)
    }

    override fun colorPresentation(params: ColorPresentationParams?):
        CompletableFuture<MutableList<ColorPresentation>> {
        return super.colorPresentation(params)
    }

    override fun foldingRange(params: FoldingRangeRequestParams?): CompletableFuture<MutableList<FoldingRange>> {
        return super.foldingRange(params)
    }

    override fun prepareRename(params: PrepareRenameParams?):
        CompletableFuture<Either3<Range, PrepareRenameResult, PrepareRenameDefaultBehavior>> {
        return super.prepareRename(params)
    }

    override fun prepareTypeHierarchy(params: TypeHierarchyPrepareParams?):
        CompletableFuture<MutableList<TypeHierarchyItem>> {
        return super.prepareTypeHierarchy(params)
    }

    override fun typeHierarchySupertypes(params: TypeHierarchySupertypesParams?):
        CompletableFuture<MutableList<TypeHierarchyItem>> {
        return super.typeHierarchySupertypes(params)
    }

    override fun typeHierarchySubtypes(params: TypeHierarchySubtypesParams?):
        CompletableFuture<MutableList<TypeHierarchyItem>> {
        return super.typeHierarchySubtypes(params)
    }

    override fun prepareCallHierarchy(params: CallHierarchyPrepareParams?):
        CompletableFuture<MutableList<CallHierarchyItem>> {
        return super.prepareCallHierarchy(params)
    }

    override fun callHierarchyIncomingCalls(params: CallHierarchyIncomingCallsParams?):
        CompletableFuture<MutableList<CallHierarchyIncomingCall>> {
        return super.callHierarchyIncomingCalls(params)
    }

    override fun callHierarchyOutgoingCalls(params: CallHierarchyOutgoingCallsParams?):
        CompletableFuture<MutableList<CallHierarchyOutgoingCall>> {
        return super.callHierarchyOutgoingCalls(params)
    }

    override fun selectionRange(params: SelectionRangeParams?): CompletableFuture<MutableList<SelectionRange>> {
        return super.selectionRange(params)
    }

    override fun semanticTokensFull(params: SemanticTokensParams?): CompletableFuture<SemanticTokens> {
        return super.semanticTokensFull(params)
    }

    override fun semanticTokensFullDelta(params: SemanticTokensDeltaParams?):
        CompletableFuture<Either<SemanticTokens, SemanticTokensDelta>> {
        return super.semanticTokensFullDelta(params)
    }

    override fun semanticTokensRange(params: SemanticTokensRangeParams?): CompletableFuture<SemanticTokens> {
        return super.semanticTokensRange(params)
    }

    override fun moniker(params: MonikerParams?): CompletableFuture<MutableList<Moniker>> {
        return super.moniker(params)
    }

    override fun inlayHint(params: InlayHintParams?): CompletableFuture<MutableList<InlayHint>> {
        return super.inlayHint(params)
    }

    override fun resolveInlayHint(unresolved: InlayHint?): CompletableFuture<InlayHint> {
        return super.resolveInlayHint(unresolved)
    }

    override fun inlineValue(params: InlineValueParams?): CompletableFuture<MutableList<InlineValue>> {
        return super.inlineValue(params)
    }

    override fun diagnostic(params: DocumentDiagnosticParams?): CompletableFuture<DocumentDiagnosticReport> {
        return super.diagnostic(params)
    }

    override fun didClose(p0: DidCloseTextDocumentParams?) {
        LOGGER.info("changed closed text document")
    }

    override fun didSave(p0: DidSaveTextDocumentParams?) {
        LOGGER.info("saved text document")
    }

    override fun connect(p0: LanguageClient?) {
        LOGGER.info("connected!")
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(KotlinTextDocumentService::class.java)
    }
}
