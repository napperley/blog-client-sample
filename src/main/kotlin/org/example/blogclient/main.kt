package org.example.blogclient

import org.webscene.client.dom.DomEditType
import org.webscene.client.dom.DomEditor
import org.webscene.client.html.HtmlSection

fun main(args: Array<String>) {
    val authPageBody = createAuthPageBody()
    val navBar = createNavigationBar()
    val footer = createFooter()

    DomEditor.editSection(HtmlSection.BODY)(footer.toDomElement(), DomEditType.PREPEND)
    DomEditor.editSection(HtmlSection.BODY)(authPageBody.toDomElement(), DomEditType.PREPEND)
    DomEditor.editSection(HtmlSection.BODY)(navBar.toDomElement(), DomEditType.PREPEND)
}