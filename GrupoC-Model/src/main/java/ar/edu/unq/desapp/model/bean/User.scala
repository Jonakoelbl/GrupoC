package ar.edu.unq.desapp.model.bean

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import javax.persistence._
import beans.BeanProperty

@Entity
@Table(name = "users")
@Inheritance(strategy=InheritanceType.JOINED)
class User (
  @BeanProperty
  var username: String,
  @BeanProperty
  var email: String,
  @BeanProperty
  var password: String) {
 
  private val serialVersionUID: Long = 1L
  
  @Id @GeneratedValue
  var id: Int = _

  @BeanProperty
  @ElementCollection
  var rols: java.util.List[String] = _
  
  @BeanProperty
  @OneToMany
  var borrowedBooks: java.util.List[Book] = _

  private def this() = this(null, null, null)
  
  def borrowBook(aBook: Book) {
    borrowedBooks = aBook :: borrowedBooks.toList
  }

  def returnBook(aBook: Book) {
    borrowedBooks = borrowedBooks filter (book => !book.equals(aBook))
    aBook.amount += 1
  }

  def commentBook(aBook: Book, aComment: String) {
    aBook.addComment(this, aComment)
  }
}
