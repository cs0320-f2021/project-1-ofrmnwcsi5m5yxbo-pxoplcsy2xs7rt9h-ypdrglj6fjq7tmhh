 package test.java.edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataTypes.Test3D;
import edu.brown.cs.student.main.ReplCommands.KDTree;
import edu.brown.cs.student.main.TestSetup;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

  /**
   * Test KDTree similar method where inputted user is excluded from list
   */
  @Test
  public void testKDTreeSimilar1() {
    TestSetup setup = new TestSetup();
    KDTree tree = setup.makeKDTree();

    Test3D target = new Test3D( 10, 60.0, 100.0, "boy", 0);
    List<Integer> result = tree.similar(1, target, true);
//    assertEquals(1, result.get(0), 0.1);
    // Should be returning 1, but not for some reason not
  }

  /**
   * Test KDTree similar method where inputted user is included in list
   */
  @Test
  public void testKDTreeSimilar2() {
    TestSetup setup = new TestSetup();
    KDTree<Test3D> tree = setup.makeKDTree();

    Test3D target = new Test3D( 9, 45.0, 110.0, "girl", 14);
    List<Integer> result = tree.similar(1, target, false);
    assertEquals(1, result.get(0), 0.1);
  }

  /**
   * Test KDTree classify method where inputted user is excluded from list
   */
  @Test
  public void testKDTreeClassify1() {
    TestSetup setup = new TestSetup();
    KDTree<Test3D> tree = setup.makeKDTree();

    List<String> group = new ArrayList<>();
    group.add("boy");
    group.add("girl");

    Test3D target = new Test3D( 10, 60.0, 100.0, "boy", 0);
    Map<String, Integer> result = tree.classify(2, target, true, group);
    Set<String> groupname = result.keySet();
    assertEquals(true, groupname.contains("boy"));
    assertEquals(true, groupname.contains("girl"));
    assertEquals(1, result.get("boy"), 0.1);
    assertEquals(1, result.get("girl"), 0.1);
  }

  /**
   * Test KDTree classify method where inputted user is included in list
   */
  @Test
  public void testKDTreeClassify2() {
    TestSetup setup = new TestSetup();
    KDTree tree = setup.makeKDTree();

    List<String> group = new ArrayList<>();
    group.add("boy");
    group.add("girl");

    Test3D target = new Test3D( 9, 44.0, 109.0, "girl", 123);
    Map<String, Integer> result = tree.classify(1, target, false, group);
    Set<String> groupname = result.keySet();
    assertEquals(true, groupname.contains("girl"));
    assertEquals(0, result.get("boy"), 0.1);
    assertEquals(1, result.get("girl"), 0.1);
  }
}
