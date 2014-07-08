package ar.edu.unq.desapp.appModel

import java.util
import ar.edu.unq.desapp.model.bean.Book
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.services.bean.BookService
import scala.beans.BeanProperty
import org.apache.wicket.spring.injection.annot.SpringBean

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class BookListAppModel(var bookService: BookService) extends Serializable with Builder {
  
  var books: java.util.List[Book] = bookService.retriveAll//new util.ArrayList

  def getBooks: java.util.List[Book] = {
    bookService.retriveAll
  }
  
  def isAvailable(aBook: Book): String = {
    if(aBook.amount > 3) "Available" else "Not available" // TODO - aBook.reservations -> get this through Service?
  }

  def getReservationsAmount(aBook: Book): Int = {
    aBook.amount 
  }

  def deleteBook(aBook: Book) {
    bookService.delete(aBook)
  }
}
