package org.example.blogclient

import org.w3c.fetch.Headers
import org.w3c.fetch.Response
import org.webscene.client.HttpMethod
import org.webscene.client.dom.DomEditType
import org.webscene.client.dom.DomEditor
import org.webscene.client.dom.DomQuery
import org.webscene.client.dom.focus
import org.webscene.client.fetchData
import org.webscene.client.html.HtmlSection
import org.webscene.client.objectsToJson
import kotlin.browser.document

fun main(args: Array<String>) {
    val authPageBody = createAuthPageBody()
    val navBar = createNavigationBar()
    val footer = createFooter()
    val authQuery = "?auth=true"

    DomEditor.editSection(HtmlSection.BODY)(footer.toDomElement(), DomEditType.PREPEND)
    DomEditor.editSection(HtmlSection.BODY)(authPageBody.toDomElement(), DomEditType.PREPEND)
    DomEditor.editSection(HtmlSection.BODY)(navBar.toDomElement(), DomEditType.PREPEND)
    DomQuery.elementById("email-txt").focus()
    if (document.location?.search == authQuery) {
        loginToServer()
    }
}

@Suppress("unused")
private fun loginToServer() {
    val reqData = arrayOf<Pair<String, *>>("user" to object {
        val email = "jake@jake.jake"
        val password = "jakejake"
    })
    val headers = Headers().apply {
        append("Content-Type", "application/json;charset=utf-8")
        append("Accept", "application/json")
    }

    println("Authenticating...")
    fetchData(
        url = "http://${Settings.SERVER_HOST}:${Settings.SERVER_PORT}/api/users/login",
        body = objectsToJson(*reqData),
        method = HttpMethod.POST,
        onError = ::loginError,
        onResponse = ::loginResponse,
        reqHeaders = headers
    )
}

private fun loginResponse(response: Response) {
    if (response.ok) println("Login successful: ${response.body}")
    else println("Login failed (HTTP ${response.status})")
}

private fun loginError(error: Throwable) {
    println("Login failed: ${error.message}")
}
