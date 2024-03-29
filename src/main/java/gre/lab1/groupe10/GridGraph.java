package gre.lab1.groupe10;

import gre.lab1.graph.GridGraph2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Graphe représentant une grille
 */
public final class GridGraph implements GridGraph2D {
  /** Largeur de la grille. */
  private final int width;

  /** Hauteur de la grille. */
  private final int height;

  /** List d'adjacence **/
  private final ArrayList<LinkedList<Integer>> adjVerticesList;

  /**
   * Construit une grille carrée.
   * @param side Côté de la grille.
   */
  public GridGraph(int side) {
    this(side, side);
  }

  /**
   * Construit une grille rectangulaire.
   * @param width Largeur de la grille.
   * @param height Hauteur de la grille.
   * @throws IllegalArgumentException si {@code width} ou {@code length} sont négatifs ou nuls.
   */
  public GridGraph(int width, int height) {
    if (width <= 0 || height <= 0)
      throw new IllegalArgumentException("Width: " + width + " and height: " + height + " must be positive");

    this.width = width;
    this.height = height;

    // Initialisation de la liste d'adjacence
    adjVerticesList = new ArrayList<>(width * height);
    for (int i = 0; i < width * height; ++i) {
      adjVerticesList.add(new LinkedList<>());
    }
  }

  /**
   * Retourne les voisins d'un sommet
   * @param v Le sommet dont on veut les voisins
   * @return Liste d'entier représentant les voisins d'un sommet
   * @throws IndexOutOfBoundsException Si les sommets n'existent pas dans le graphe
   */
  @Override
  public List<Integer> neighbors(int v) throws IndexOutOfBoundsException {
    if (!vertexExists(v)) {
      throw new IndexOutOfBoundsException("L'index v n'éxiste pas.");
    }

    return adjVerticesList.get(v);
  }

  /**
   * Vérification que deux sommets sont dans le graphe
   * @param u Premier sommet à vérifier
   * @param v Deuxième sommet à vérifier
   * @throws IndexOutOfBoundsException Si les sommets n'existent pas dans le graphe
   */
  private void areInBound(int u, int v) throws IndexOutOfBoundsException {
    if (!vertexExists(u) || !vertexExists(v)) {
      throw new IndexOutOfBoundsException("u or v doesn't exist.");
    }
  }

  /**
   * Vérifie que deux sommets son adjacent
   * @param u Premier sommet
   * @param v Deuxième sommet.
   * @return Si le premier sommet est adjacent avec le deuxième et vice-versa
   */
  @Override
  public boolean areAdjacent(int u, int v) {
    areInBound(u, v);

    return adjVerticesList.get(u).contains(v) && adjVerticesList.get(v).contains(u);
  }

  /**
   * Lie deux sommets par une arête
   * @param u Une extrémité de l'arête.
   * @param v L'autre extrémité de l'arête.
   */
  @Override
  public void addEdge(int u, int v) {
    areInBound(u, v);

    if (!adjVerticesList.get(u).contains(v)) {
      adjVerticesList.get(u).add(v);
    }
    if (!adjVerticesList.get(v).contains(u)) {
      adjVerticesList.get(v).add(u);
    }
  }

  /**
   * Dissocie deux sommets d'une arête
   * @param u Une extrémité de l'arête.
   * @param v L'autre extrémité de l'arête.
   * @throws IllegalArgumentException Si les sommets ne sont pas adjacents
   */
  @Override
  public void removeEdge(int u, int v) throws IllegalArgumentException {
    areInBound(u, v);

    if (!areAdjacent(u, v)){
      throw new IllegalArgumentException("u and v aren't adjacent.");
    }

    adjVerticesList.get(u).removeFirstOccurrence(v);
    adjVerticesList.get(v).removeFirstOccurrence(u);
  }

  /**
   * Retourne le nombre de sommets
   * @return Le nombre de sommets
   */
  @Override
  public int nbVertices() {
    return width * height;
  }

  /**
   * Indique si un sommet existe dans le graphe
   * @param v Un sommet.
   * @return Si un sommet existe dans le graphe
   */
  @Override
  public boolean vertexExists(int v) {
    return (v >= 0 && v < height * width);
  }

  /**
   * La largeur de la grille
   * @return Largeur de la grille
   */
  @Override
  public int width() {
    return width;
  }

  /**
   * La hauteur de la grille
   * @return Hauteur de la grille
   */
  @Override
  public int height() {
    return height;
  }

  /**
   * Lie chaque sommet du graphe donné à tous ses voisins dans la grille.
   * @param graph Un graphe.
   */
  public static void bindAll(GridGraph graph) {
    for (int i = 0; i < graph.width * graph.height; ++i){
      // Lie chaque sommet avec tous ses voisins
      List<Integer> neightborsValues = new ArrayList<>();
      if (i >= graph.width) neightborsValues.add(i - graph.width); // Si le voisin du dessus existe
      if (i < graph.width * (graph.height - 1)) neightborsValues.add(i + graph.width); // Si le voisin du dessous existe
      if (i % graph.height != 0) neightborsValues.add(i - 1); // Si le voisin à gauche existe
      if (i % graph.height != graph.height - 1) neightborsValues.add(i + 1); // Si le voisin à droite existe
      for (Integer neighbor : neightborsValues) {
        graph.addEdge(i, neighbor);
      }
    }
  }
}
