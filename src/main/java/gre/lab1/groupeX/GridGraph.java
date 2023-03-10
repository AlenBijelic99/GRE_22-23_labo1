package gre.lab1.groupeX;

import gre.lab1.graph.GridGraph2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO: javadoc
public final class GridGraph implements GridGraph2D {
  /** Largeur de la grille. */
  private final int width;

  /** Hauteur de la grille. */
  private final int height;


  private LinkedList<Integer>[] adjVerticesList;

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

    adjVerticesList = new LinkedList[width];
  }

  @Override
  public List<Integer> neighbors(int v) {
    if (v < 0 || v >= height * width) {
      throw new IndexOutOfBoundsException("L'index v n'éxiste pas.");
    }
    List<Integer> neightborsValues = new ArrayList<>();
    if (v >= width) neightborsValues.add(v - width); // Check for upper neighbor
    if (v < width * (height - 1)) neightborsValues.add(v + width); // Check for under neighbor
    if (v % height != 0) neightborsValues.add(v - 1); // Check for left hand sign neighbor
    if (v % height != height - 1) neightborsValues.add(v + 1); // Check for right hand sign neighbor
    return neightborsValues;
  }

  @Override
  public boolean areAdjacent(int u, int v) {
    if (vertexExists(u) && vertexExists(v)) {
      throw new IndexOutOfBoundsException("u or v doesn't exist.");
    }
    // Adjacent if v is next, above or below to u.
    return (u == v - 1 || u == v + 1 || u == v - width || u == v + width);
  }

  @Override
  public void addEdge(int u, int v) {
    if (vertexExists(u) && vertexExists(v)) {
      throw new IndexOutOfBoundsException("u or v doesn't exist.");
    }

    if (!areAdjacent(u, v)){
      throw new IllegalArgumentException("u and v aren't adjacent.");
    }


    //adjVerticesList.get(u).add(v);

  }

  @Override
  public void removeEdge(int u, int v) {
    // TODO: A implémenter
  }

  @Override
  public int nbVertices() {
    return height * width;
  }

  @Override
  public boolean vertexExists(int v) {
    return (v < 0 || v >= height * width);
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
