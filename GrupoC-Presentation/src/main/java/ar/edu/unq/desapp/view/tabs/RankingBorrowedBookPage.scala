package ar.edu.unq.desapp.view.tabs

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.PropertyListView
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.CompoundPropertyModel
import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.services.bean.BookService
import ar.edu.unq.desapp.view.model.BasePage
import ar.edu.unq.desapp.services.GeneralService
import scala.beans.BeanProperty
import org.apache.wicket.spring.injection.annot.SpringBean
import org.apache.wicket.markup.html.basic.Label

class RankingBorrowedBookPage extends BasePage {
  
  @BeanProperty @SpringBean(name = "services.book")
  var bookService: BookService = _
  
  val rbb = new RankingBorrowedBookAppModel(bookService)
  val form = new Form[RankingBorrowedBookAppModel]("rankingBorrowedBookForm", new CompoundPropertyModel[RankingBorrowedBookAppModel](rbb))
  this.add(form)
  this.createTableRanking(form)
  
  protected def createTableRanking(form: Form[RankingBorrowedBookAppModel]) {
    form.add(new PropertyListView[Book]("retriveAllMostBorrowed") {
      val serialVersionUID: Long = 1L

      override def populateItem(item: ListItem[Book]) {
        item.add(new Label("title"))
        item.add(new Label("isbn"))
//        item.add(label("editorial"))
//        item.add(label("description"))
//        item.add(image)
      }
    })
  }
}

class RankingBorrowedBookAppModel(bookService: BookService) {
  def retriveAllMostBorrowed(): java.util.List[Book] = {
    bookService.retriveAllMostBorrowed
  }
}