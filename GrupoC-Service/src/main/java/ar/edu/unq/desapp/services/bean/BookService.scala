package ar.edu.unq.desapp.services.bean

import ar.edu.unq.desapp.services.GenericService
import ar.edu.unq.desapp.model.bean.Book
import javax.annotation.Resource
import ar.edu.unq.desapp.repository.bean.BookRepository
import scala.beans.BeanProperty
import ar.edu.unq.desapp.repository.bean.LoanBookRepository
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import ar.edu.unq.desapp.model.bean.LoanBook

class BookService extends GenericService[Book] {

  private val serialVersionUID: Long = 1L
  
  @BeanProperty @Resource
  var loanBookRepository: LoanBookRepository = _
  
  def retriveAllMostBorrowed: java.util.List[Book] = {
	val loanBooks: java.util.List[LoanBook] = loanBookRepository.findAll
	var allMostBorrowedBooks: Map[Book, Int] = Map()
	for(loan <- loanBooks) { //I know, it's horrible
	  if(allMostBorrowedBooks.contains(loan.book)) {
	    allMostBorrowedBooks.updated(loan.book, allMostBorrowedBooks.get(loan.book))
	  }
	  else {
	    allMostBorrowedBooks += (loan.book -> 1)
	  }
	}
	allMostBorrowedBooks.keySet.toList
  }
}
