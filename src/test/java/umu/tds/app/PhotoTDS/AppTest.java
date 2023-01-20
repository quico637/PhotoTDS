package umu.tds.app.PhotoTDS;

import org.junit.jupiter.api.Test;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;


public class AppTest {
    
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }
  
  @Test
  public void testFollower() {
	  
	  String u1 = "quica";
	  String u2 = "jc";
	  
	  String u1Pass = "quica";
	  String u2Pass = "jcjcjcjc";
	  
	  assertTrue(Controller.getInstancia().login(u1, u1Pass));
	  
	  Optional<User> userOpt = Controller.getInstancia().getUser(u2);
	  assertTrue(userOpt.isPresent());
	  
	  User u = userOpt.get();
	  
	  int followersBefore = u.getNumFollowers();
	  
	  assertTrue(Controller.getInstancia().follow(u1, u2));
	  
	  int followersAfter = u.getNumFollowers();
	  
	  Optional<User> userOpt2 = Controller.getInstancia().getUser(u1);
	  
	  assertTrue(userOpt2.isPresent());
	  
	  User user2 = userOpt2.get();
	  assertTrue(u.getUsuariosSeguidores().contains(user2));
	  
  }
  
  
  @Test
  public void testChangeDescription() {
	  
	  String u1 = "quica";
	  
	  String u1Pass = "quica";
	  
	  assertTrue(Controller.getInstancia().login(u1, u1Pass));
	  
	  Optional<User> userOpt = Controller.getInstancia().getUser(u1);
	  if(userOpt.isEmpty())
		  return;
	  
	  User u = userOpt.get();
	  
	  String descBefore = u.getDescripcion();
	  String newDesc = "desc changed";
	  
	  Controller.getInstancia().changeDescription(u1, newDesc);
	  
	  String descAfter = u.getDescripcion();
	  
	  assertEquals(descAfter, newDesc);
  }
  
  /**
   * Checks if the user will be found in the finder tab.
   */
  @Test
  public void testCheckFinder() {
	  String userLogged = "quica";
	  String userToFind = "jc";
	  
	  String u1Pass = "quica";
	  String u2Pass = "jcjcjcjc";
	  
	  assertTrue(Controller.getInstancia().login(userLogged, u1Pass));
	  
	  
	  User u = (User) Controller.getInstancia().getBusqueda(userLogged, userToFind).get(0);
	  
	  assertEquals(u.getUsername(), userToFind);
  }
  
}
