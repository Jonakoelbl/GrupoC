package ar.edu.unq.desapp.repository.bean

import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO
import ar.edu.unq.desapp.model.bean.LoanBook

import org.hibernate.criterion.Restrictions
import org.hibernate.criterion.Projections

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

class LoanBookRepository extends HibernateGenericDAO[LoanBook] {

  persistentClass = classOf[LoanBook]
  
  def findTheTwentyMostBorrowedBook: java.util.List[LoanBook] = {
    var criteria = this.getSession().createCriteria(this.persistentClass)
    var proList = Projections.projectionList()
    
    proList.add(Projections.distinct(Projections.countDistinct("book")))
    criteria.setProjection(proList).list().asInstanceOf[java.util.List[LoanBook]]
  }
}
