package generator;

import generator.model.Branch;
import generator.model.Module;
import generator.model.Query;
import generator.model.Road;

import java.util.*;

public class StreetGenerator {

  private GlobalGoals globalGoals;
  private LocalConstraints localConstraints;
  private LinkedList<Module> modules;

  public static void main(String[] args) {
    System.out.println("hej");
  }

  public StreetGenerator() {
    modules = new LinkedList<Module>();
  }

  public LinkedList<Module> generate() {
    // call step() until done
    return null;
  }

  private void step() {
    ListIterator<Module> it = modules.listIterator();

    while (it.hasNext()) {
      Module current = it.next();

      if (current instanceof Road) {
        Road currentRoad = (Road) current;

        // P1
        if (currentRoad.delay < 0) {
          System.out.println("P1");
          it.remove();
          continue;
        }

        // P2 and P3
        if (it.hasNext()) {
          Module next = it.next();
          if (next instanceof Query) {
            Query nextQuery = (Query) next;
            if (nextQuery.state == Query.SUCCEED) {
              System.out.println("P2");

              // TODO
              // call global goals to set parameters in rule- and roadattr
              // add angle, forward, branch, branch and road to modules list


              continue;
            
            } else if (nextQuery.state == Query.FAILED) {
              System.out.println("P3");
              it.remove();
              continue;
            }
          }
        }

        // P7
        if (it.hasPrevious()) {
          Module previous = it.previous();
          if (previous instanceof Query) {
            Query prevQuery = (Query) previous;
            if (currentRoad.delay < 0) {
              System.out.println("P7");
              it.remove();
              continue;
            }
          }
        }
        


      } else if (current instanceof Branch) {
        Branch currentBranch = (Branch) current;

        // P4
        if (currentBranch.delay > 0) {
          System.out.println("P4");
          currentBranch.delay--;
          continue;
        }

        // P5
        if (currentBranch.delay == 0) {
          // TODO: replace with road and query module
          continue;
        }

        // P6
        if (currentBranch.delay < 0) {
          it.remove();
          continue;
        }

      } else if (current instanceof Query) {
        Query currentQuery = (Query) current;

        if (currentQuery.state == Query.UNASSIGNED) {
          // TODO: run local constraints to set parameters and state

          continue;
        
        } else {
          it.remove();
          continue;
        }

      } else {
        // do nothing
      }
    }
  }
}