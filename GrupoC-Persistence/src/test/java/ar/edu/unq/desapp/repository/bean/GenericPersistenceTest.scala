package ar.edu.unq.desapp.repository.bean

import org.scalatest.FunSpec
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.ShouldMatchers
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestContextManager
import ar.edu.unq.desapp.repository.generic.HibernateGenericDAO
import ar.edu.unq.desapp.utils.builder.Builder
import ar.edu.unq.desapp.utils.builder.BuilderToTest
import javax.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.scalatest.BeforeAndAfter

@ContextConfiguration( Array("classpath:/META-INF/spring-persistence-context.xml", "classpath:/spring/*.xml"))
class GenericPersistenceTest extends FunSpec with ShouldMatchers with GivenWhenThen with BeforeAndAfter with Builder {

  new TestContextManager(classOf[GenericPersistenceTest]).prepareTestInstance(this)
  
  @Resource(name="hibernateGeneric")
  var repository: HibernateGenericDAO[java.lang.Object] = _
  
  var entities = List(
    		anUser.withName("Nadie").withEmail("Ninguno").withPassword("Boludo"),
    		aBook.withTitle("book?").withEditorial("what editorial?").withIsbn("what is that?").withAmount(2)
    		)
    		
  describe("Persistence all Entities ") {
    it("all entities should be persisted") {
    	val builders = entities 
    	for(builder <- builders) {
    	  val obj = builder.build
    	  try {
    		  repository.save(obj)
    	  } catch {
    	    case e: Exception => fail("the entity couldn't persist: " + e.getMessage())
    	  }
    	}
    }
    
    ignore("Save and retrieve all entities") {
      val builders = entities
      
      for(builder <- builders) {
        val obj = builder.build
        
        try {
        	repository.save(obj)
        	repository.getSessionFactory().getCurrentSession().flush()
          
        	val obj_from_DB = repository.findByExample(obj)(0)
        	obj_from_DB should equal(obj)
        	repository.delete(obj)
        } catch {
          case e: Exception => fail("test failed due to: " + e.getMessage() + e.getCause())
        }
      }
    }
  }
}