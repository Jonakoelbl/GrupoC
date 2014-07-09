package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO
import ar.edu.unq.desapp.model.bean.LoanBook

import org.hibernate.criterion.Restrictions
import org.hibernate.criterion.Projections

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class LoanBookRepository extends HibernateGenericDAO[LoanBook] {

  persistentClass = classOf[LoanBook]
}
