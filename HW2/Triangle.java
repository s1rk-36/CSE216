import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Triangle implements TwoDShape, Positionable {

    private List<TwoDPoint> vertices;

    public Triangle(List<TwoDPoint> vertices) {
        if (vertices.size() < 3)
            throw new IllegalArgumentException("Not enough points to make a triangle");
        this.vertices = new ArrayList<>();
        setPosition(vertices);
        // TODO
    }

    /**
     * Sets the position of this triangle according to the first three elements in the specified list of points. The
     * triangle is formed on the basis of these three points taken in a clockwise manner on the two-dimensional
     * x-y plane, starting with the point with the least x-value. If the input list has more than three elements, the
     * subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        List<TwoDPoint> convertedPoints = (List<TwoDPoint>) points.stream()
                .collect( Collectors.toList());
        List<TwoDPoint> finalPoints = new ArrayList<>();
        TwoDPoint min = convertedPoints.get(0);
        TwoDPoint second;
        TwoDPoint third;
        int counter = 0;
        for (int i = 0; i < 3; i++){
            if ((min.getX()) == (convertedPoints.get(i).getX()) && (min.getY()) > (convertedPoints.get(i).getY())) {
                min = convertedPoints.get(i);
                counter++;
            } else if((min.getX()) > (convertedPoints.get(i).getX())) {
                min = convertedPoints.get(i);
                counter++;
            }
        }
        finalPoints.add(min);
        convertedPoints.remove(counter);

        if ((convertedPoints.get(0).getY()) > (convertedPoints.get(1).getY())){
            second = convertedPoints.get(0);
            third = convertedPoints.get(1);
        } else{
            second = convertedPoints.get(1);
            third = convertedPoints.get(0);
        }
        finalPoints.add(second);
        finalPoints.add(third);

        for (int i = 0; i < 3; i++)
            vertices.add(finalPoints.get(i));

        // TODO
    }

    /**
     * Retrieve the position of an object as a list of points. The points are be retrieved and added to the returned
     * list in a clockwise manner on the two-dimensional x-y plane, starting with the point with the least x-value. If
     * two points have the same least x-value, then the clockwise direction starts with the point with the lower y-value.
     *
     * @return the retrieved list of points.
     */
    @Override
    public List<? extends Point> getPosition() {
        return vertices; // TODO
    }

    /**
     * @return the number of sides of this triangle, which is always set to three
     */
    @Override
    public int numSides() {
        return 3;
    }

    /**
     * Checks whether or not a list of vertices forms a valid triangle. The <i>trivial</i> triangle, where all three
     * corner vertices are the same point, is considered to be an invalid triangle.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a triangle, and
     * <code>false</code> otherwise. For example, three vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        List<TwoDPoint> points = (List<TwoDPoint>) vertices;
        Triangle triangle = new Triangle(points);
        if (triangle.area() == 0)
            return false;
        else
            return true; // TODO
    }

    /**
     * This method snaps each vertex of this triangle to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant triangle will thus have all three
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * triangle becoming invalid (e.g., all corners collapse to a single point), then it is left unchanged. Snapping is
     * an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        List <TwoDPoint> points = vertices.stream().collect(Collectors.toList());
        for (int i = 0; i < 3; i++) {
            double x = Math.round(points.get(i).getX());
            double y = Math.round(points.get(i).getY());
            points.get(i).setX(x);
            points.get(i).setY(y);
        }

        if (!isMember(points))
            new Triangle(vertices);
        else
            new Triangle(points);

        // TODO
    }

    /**
     * @return the area of this triangle
     */
    public double area() {
        return Math.abs(((vertices.get(0).getX() * ((vertices.get(1).getY()) - (vertices.get(2).getY())))
                + (vertices.get(1).getX() * ((vertices.get(2).getY()) - (vertices.get(0).getY())))
        + (vertices.get(2).getX() * ((vertices.get(0).getY()) - (vertices.get(1).getY()))))) / 2; // TODO
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this triangle
     */
    public double perimeter() {
        double sum = 0;
        double side1 = Math.sqrt(Math.pow(((vertices.get(1).getX()) - (vertices.get(0).getX())), 2) +
                Math.pow((vertices.get(1).getY() - vertices.get(0).getY()), 2));
        double side2 = Math.sqrt(Math.pow(((vertices.get(2).getX()) - (vertices.get(1).getX())), 2) +
                Math.pow(((vertices.get(2).getY()) - (vertices.get(1).getY())), 2));
        double side3 = Math.sqrt(Math.pow(((vertices.get(2).getX()) - (vertices.get(0).getX())), 2) +
                Math.pow(((vertices.get(2).getY()) - (vertices.get(0).getY())), 2));
        sum = side1 + side2 + side3;
        return Math.abs(sum); // TODO
    }

    public String toString(){
        if (!isMember(vertices))
            throw new IllegalArgumentException("Not a valid Triangle");

        String triangle = "Triangle[";
        for (int i = 0; i < 3; i++){
            if (i == 2){
                triangle += "(" + vertices.get(i).getX() + ", " + vertices.get(i).getY() + ")]";
                continue;
            }
            triangle += "(" + vertices.get(i).getX() + ", " + vertices.get(i).getY() + "), ";
        }
        return triangle;
    }
}
