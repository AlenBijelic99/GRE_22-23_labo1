package gre.lab1.groupe10;

import gre.lab1.graph.Graph;
import gre.lab1.graph.VertexLabelling;
import gre.lab1.gui.MazeGenerator;
import gre.lab1.gui.MazeBuilder;
import gre.lab1.gui.Progression;

import java.util.*;

/**
 * Générateur de labyrinthe
 */
public final class DFSMazeGenerator implements MazeGenerator {
    /**
     * Algoritme DFS permettant de génèrer un labyrinthe en supprimant les murs entre deux sommets après avoir visité tous les sommets voisins
     *
     * @param builder Un builder à qui déléguer les modifications de la structure de données.
     * @param from    Sommet de départ, si l'algorithme utilisé en nécessite un.
     */
    @Override
    public void generate(MazeBuilder builder, int from) {
        // Récupération du graphe et du label permettant d'indiquer à l'interface l'état de la progression
        Graph graph = builder.topology();
        VertexLabelling<Progression> label = builder.progressions();

        // Défini si un sommet est visité
        boolean[] visited = new boolean[graph.nbVertices()];
        Arrays.fill(visited, false);

        // Pile LIFO permettant de stocker les sommets à visiter
        Stack<Integer> stack = new Stack<>();

        // Commence avec le sommet de départ
        int lastVisited = from;
        stack.push(from);
        visited[from] = true;
        label.setLabel(from, Progression.PROCESSING);

        // Traiter les sommets restants dans la pile
        while (!stack.isEmpty()) {
            // Récupération du dernier élément ajouté à la pile
            int current = stack.peek();
            // Récupération des voisins du sommet actuel et éffectue mélange pour obtenir la liste des voisins dans un ordre aléatoire
            List<Integer> neighbors = graph.neighbors(current);
            Collections.shuffle(neighbors);

            boolean hasNeighborToVisit = false;
            for (int neighbor : neighbors) {
                // On traite le premier voisin non visité dans la liste.
                if (!visited[neighbor]) {
                    visited[neighbor] = true;

                    // Supprimer le mur entre le sommet courant et le voisin
                    //builder.removeWall(current, neighbor);

                    // Ajouter le voisin à la pile et le marquer en traitement
                    stack.push(neighbor);
                    label.setLabel(neighbor, Progression.PROCESSING);
                    hasNeighborToVisit = true;
                    break;
                }
            }
            if (!hasNeighborToVisit) {
                label.setLabel(current, Progression.PROCESSED);
                lastVisited = stack.pop();
                if (!stack.empty()) {
                    builder.removeWall(stack.peek(), lastVisited);
                }
            }
        }
    }
}
