package org.example.blogclient

import org.webscene.client.HttpMethod
import org.webscene.client.html.InputType
import org.webscene.client.html.ParentHtmlTag
import org.webscene.client.html.bootstrap.Bootstrap
import org.webscene.client.html.bootstrap.ButtonSize
import org.webscene.client.html.HtmlCreator as html

internal fun createAuthPageBody(signup: Boolean = false) = html.parentElement("div") {
    classes += "auth-page"
    children += Bootstrap.container {
        classes += "page"
        row {
            // Column.
            parentHtmlElement("div") {
                classes.addAll(arrayOf("col-md-6", "offset-md-3", "col-xs-12"))
                children.addAll(createColumnItems(signup))
            }
        }
    }
}

private fun createColumnItems(signup: Boolean) = arrayOf(
    html.parentElement("h1") {
        classes += "text-xs-center"
        if (signup) +"Signup" else +"Login"
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
        parentHtmlElement("li") {
            attributes["hidden"] = "true"
            +"That email is already taken"
        }
    },
    createForm(signup)
)

private fun createFieldSets(signup: Boolean): Array<ParentHtmlTag> {
    val namePos = 0
    val emailPos = 1
    val placeholders = arrayOf("Your Name", "Email", "Password")
    val tmpFieldSets = mutableListOf<ParentHtmlTag>()

    placeholders.forEach { p ->
        tmpFieldSets += html.parentElement("fieldset") {
            classes += "form-group"
            children += html.input(type = InputType.TEXT) {
                classes.addAll(arrayOf("form-control", "form-control-lg"))
                attributes["placeholder"] = p
                if (attributes["placeholder"] == placeholders[emailPos]) id = "email-txt"
                if (!signup && attributes["placeholder"] == placeholders[namePos]) attributes["hidden"] = "true"
            }
        }
    }
    return tmpFieldSets.toTypedArray()
}

private fun createForm(signup: Boolean) = html.form(action = "?auth=true", method = HttpMethod.POST) {
    children.addAll(createFieldSets(signup))
    parentHtmlElement("button") {
        id = "action-btn"
        classes.addAll(arrayOf("btn", ButtonSize.LARGE.txt, "btn-primary", "pull-xs-right"))
        if (signup) +"Signup" else +"Login"
    }
}