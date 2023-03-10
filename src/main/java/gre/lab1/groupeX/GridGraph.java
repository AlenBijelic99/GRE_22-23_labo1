package gre.lab1.groupeX;

import gre.lab1.graph.GridGraph2D;

import java.util.List;

// TODO: javadoc
public final class GridGraph implements GridGraph2D {
  /** Largeur de la grille. */
  private final int width;

  /** Hauteur de la grille. */
  private final int height;

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
  }

  @Override
  public List<Integer> neighbors(int v) {
    // TODO: A implémenter
    return null;
  }

  @Override
  public boolean areAdjacent(int u, int v) {
    // TODO: A implémenter
    return false;
  }

  @Override
  public void addEdge(int u, int v) {
    // TODO: A implémenter
  }

  @Override
  public void removeEdge(int u, int v) {
    // TODO: A implémenter
  }

  @Override
  public int nbVertices() {
    // TODO: A implémenter
    return 0;
  }

  @Override
  public boolean vertexExists(int v) {
    // TODO: A implémenter
    return false;
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  /**
   * Lie chaque sommet du graphe donné à tous ses voisins dans la grille.
   * @param graph Un graphe.
   */
  public static void bindAll(GridGraph graph) {
    // TODO: A implémenter
  }
}
