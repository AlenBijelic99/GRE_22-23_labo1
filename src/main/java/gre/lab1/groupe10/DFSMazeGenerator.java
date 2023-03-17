package gre.lab1.groupe10;

import gre.lab1.graph.Graph;
import gre.lab1.graph.VertexLabelling;
import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.*;

// TODO: javadoc
public final class DFSMazeGenerator implements MazeGenerator {
  @Override
  public void generate(MazeBuilder builder, int from) {
    // TODO: A implémenter
    //  NOTES D'IMPLÉMENTATION :
    //  Afin d'obtenir l'affichage adéquat, indiquer la progression (en tant que label du sommet traité) :
    //  - PROCESSING, en pré-traitement;
    //  - PROCESSED, en post-traitement.
    //  Le labyrinthe n'a que des murs au début de la construction, il faut donc créer les passages en
    //  supprimant des murs.

    Graph graph = builder.topology();

    // Marquer tous les sommets comme non visités
    boolean[] visited = new boolean[graph.nbVertices()];
    Arrays.fill(visited, false);

    // Initialiser la pile de parcours
    Stack<Integer> stack = new Stack<>();

    // Traiter le sommet initial
    stack.push(from);

    VertexLabelling<Progression> label = builder.progressions();
    int lastVisited = from;
    while (!stack.isEmpty()) {
      int current = stack.pop();
      label.setLabel(current, Progression.PROCESSING);
      List<Integer> neighbors = graph.neighbors(current);
      Collections.shuffle(neighbors);
      for (int neighbor : neighbors) {
        if (!visited[neighbor]) {
          stack.push(neighbor);
          visited[neighbor] = true;
        } else if (graph.areAdjacent(current, neighbor) && neighbor != lastVisited) {
          builder.removeWall(current, neighbor);
        }
      }
      label.setLabel(current, Progression.PROCESSED);
      lastVisited = current;
    }
  }
}
