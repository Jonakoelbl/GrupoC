package ar.edu.unq.desapp.services.bean

import ar.edu.unq.desapp.model.bean.LoanBook
import ar.edu.unq.desapp.services.GenericService
import org.joda.time.DateTime
import org.springframework.transaction.annotation.Transactional
import ar.edu.unq.desapp.model.bean.{ User, Book }
import scala.beans.BeanProperty
import javax.annotation.Resource

class LoanBookService extends GenericService[LoanBook] {

  @BeanProperty @Resource
  var userService: UserService = _

  @Transactional
  def recordLoan(username: String, aBook: Book) {
    val user = userService.findByUsername(username)
    if (user != null) {
      aBook.amount -= 1
      val refundDate = new DateTime().plusDays(3)//TODO define loan to day
      val loanBook = new LoanBook(new DateTime, refundDate)
      loanBook.user = user
      loanBook.book = aBook
      this.save(loanBook)
    }
  }
}