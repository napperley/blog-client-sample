package org.example.blogclient

import org.webscene.client.html.ParentHtmlTag
import org.webscene.client.html.HtmlCreator as html

internal fun createNavigationBar() = html.parentElement("nav") {
    classes.addAll(arrayOf("navbar", "navbar-light"))
    parentHtmlElement("div") {
        classes += "container"
        parentHtmlElement("a") {
            classes += "navbar-brand"
            attributes["href"] = "index.html"
            +"Conduit"
        }
        children += createList()
    }
}

internal fun createFooter() = html.parentElement("footer") {
    parentHtmlElement("div") {
        classes += "container"
        parentHtmlElement("a") {
            attributes["href"] = ""
            classes += "logo-font"
            +"conduit"
        }
        parentHtmlElement("span") {
            classes += "attribution"
            parentHtmlElement("span") { +"An interactive learning project from " }
            children += createThinksterLink()
            parentHtmlElement("span") { +". Code & design licensed under MIT" }
        }
    }
}

private fun createThinksterLink() = html.parentElement("a") {
    attributes["href"] = "https://thinkster.io"
    +"Thinkster"
}

private fun createList() = html.parentElement("ul") {
    classes.addAll(arrayOf("nav", "navbar-nav", "pull-xs-right"))
    children.addAll(createListItems())
}

private fun createListItems(): Array<ParentHtmlTag> {
    val items = arrayOf(
        html.parentElement("a") {
            classes.addAll(arrayOf("nav-link", "active"))
            attributes["href"] = ""
            +"Home"
        },
        html.parentElement("a") {
            classes += "nav-link"
            attributes["href"] = ""
            parentHtmlElement("i") {
                classes += "ion-compose"
            }
            +" New Post"
        },
        html.parentElement("a") {
            classes += "nav-link"
            attributes["href"] = ""
            parentHtmlElement("i") {
                classes += "ion-gear-a"
            }
            +" Settings"
        },
        html.parentElement("a") {
            classes += "nav-link"
            attributes["href"] = ""
            +"Sign up"
        }
    )

    return items.map { i ->
        html.parentElement("li") {
            classes += "nav-item"
            children += i
        }
    }.toTypedArray()
}