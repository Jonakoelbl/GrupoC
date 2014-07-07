package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO
import ar.edu.unq.desapp.model.bean.Book
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import org.hibernate.Criteria
import org.hibernate.criterion.Restrictions
import javax.persistence.Query

class BookRepository extends HibernateGenericDAO[Book] {

  private val serialVersionUID: Long = 1L

  persistentClass = classOf[Book]
}
