package ar.edu.unq.desapp.view.model

import ar.edu.unq.desapp.appModel.BookListAppModel
import ar.edu.unq.desapp.services.GeneralService
import ar.edu.unq.desapp.view.tabs.{RankingBorrowedBookPage, BookListPage}
import org.apache.wicket.markup.html.form.{Button, Form, TextField}
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean

import scala.beans.BeanProperty

class BasePage extends HeadBlankPage {

  @BeanProperty @SpringBean(name = "services.general")
  var generalService: GeneralService = _
  val appModel = new BookListAppModel(generalService.bookService)

  override def onInitialize() {
    super.onInitialize()

    val form = new Form[BookListAppModel]("form", new CompoundPropertyModel(this.appModel))
    addSearcher(form)

    addLinkToHome()
    addProfileLink()
    addMainOptions()
    addLogoutOption()
  }

  private def addLinkToHome() {
    add(new BookmarkablePageLink[HomePage]("linkToHomePage", classOf[HomePage]))
  }

  private def addSearcher(parent: Form[BookListAppModel]) {
    add(new TextField("search"))
    add(new Button("btn-search") { override def onSubmit() { /*TODO*/ } })
  }

  private def addProfileLink() {
    add(new BookmarkablePageLink("profile", classOf[ProfilePage]))
  }
  
  private def addMainOptions() {
    add(new BookmarkablePageLink("BookList", classOf[BookListPage]))
	  add(new BookmarkablePageLink("RankingBorrowedBook", classOf[RankingBorrowedBookPage]))
    add(new BookmarkablePageLink("AddBook", classOf[AddEditBookPage]))
    add(new BookmarkablePageLink("AddApiBook", classOf[AddApiBookPage]))
  }

  private def addLogoutOption() {
    add(new BookmarkablePageLink("logout", classOf[LoginPage]))
  }
}
