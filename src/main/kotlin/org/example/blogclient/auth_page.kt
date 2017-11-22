package org.example.blogclient

import org.webscene.client.HttpMethod
import org.webscene.client.html.InputType
import org.webscene.client.html.ParentHtmlTag
import org.webscene.client.html.bootstrap.Bootstrap
import org.webscene.client.html.bootstrap.ButtonSize
import org.webscene.client.html.HtmlCreator as html

internal fun createAuthPageBody() = html.parentElement("div") {
    classes += "auth-page"
    children += Bootstrap.container {
        classes += "page"
        row {
            // Column.
            parentHtmlElement("div") {
                classes.addAll(arrayOf("col-md-6", "offset-md-3", "col-xs-12"))
                children.addAll(createColumnItems())
            }
        }
    }
}

private fun createColumnItems() = arrayOf(
    html.parentElement("h1") {
        classes += "text-xs-center"
        +"Sign up"
    },
    html.parentElement("p") {
        classes += "text-xs-center"
        parentHtmlElement("a") {
            attributes["href"] = ""
            +"Have an account?"
        }
    },
    html.parentElement("ul") {
        classes += "error-messages"
        parentHtmlElement("li") { +"That email is already taken" }
    },
    createForm()
)

private fun createFieldSets(): Array<ParentHtmlTag> {
    val placeholders = arrayOf("Your Name", "Email", "Password")
    val tmpFieldSets = mutableListOf<ParentHtmlTag>()

    placeholders.forEach { p ->
        tmpFieldSets += html.parentElement("fieldset") {
            classes += "form-group"
            children += html.input(type = InputType.TEXT) {
                classes.addAll(arrayOf("form-control", "form-control-lg"))
                attributes["placeholder"] = p
            }
        }
    }
    return tmpFieldSets.toTypedArray()
}

private fun createForm() = html.form(action = "", method = HttpMethod.GET) {
    children.addAll(createFieldSets())
    parentHtmlElement("button") {
        classes.addAll(arrayOf("btn", ButtonSize.LARGE.txt, "btn-primary", "pull-xs-right"))
        +"Sign up"
    }
}