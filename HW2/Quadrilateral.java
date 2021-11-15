import java.util.*;
import java.util.stream.Collectors;

public class Quadrilateral implements TwoDShape, Positionable {

    List<TwoDPoint> vertices;

    public Quadrilateral(List<TwoDPoint> vertices) {
        if (vertices.size() < 4)
            throw new IllegalArgumentException("Not enough points to make a quadrilateral");
        this.vertices = new ArrayList<>();
        setPosition(vertices);
        // TODO
    }

    /**
     * Sets the position of this quadrilateral according to the first four elements in the specified list of points. The
     * quadrilateral is formed on the basis of these four points taken in a clockwise manner on the two-dimensional
     * x-y plane, starting with the point with the least x-value. If the input list has more than four elements, the
     * subsequent elements are ignored.
     *
     * @param points the specified list of points.
     */
    @Override
    public void setPosition(List<? extends Point> points) {
        points = points.subList(0, 4);
        List<TwoDPoint> convertedPoints = orderByRows((List<TwoDPoint>) points.stream().collect(Collectors.toList()));
        vertices = (List<TwoDPoint>) points.stream()
                .collect( Collectors.toList());
        if (convertedPoints.get(0).getX() > convertedPoints.get(1).getX()){
            TwoDPoint temp = convertedPoints.get(0);
            vertices.set(0, convertedPoints.get(1));
            vertices.set(3, temp);
        } else if(convertedPoints.get(0).getX() < convertedPoints.get(1).getX()){
            TwoDPoint temp = convertedPoints.get(1);
            vertices.set(0, convertedPoints.get(0));
            vertices.set(3, temp);
        }

        if (convertedPoints.get(2).getX() < convertedPoints.get(3).getX()){
            TwoDPoint temp = convertedPoints.get(2);
            vertices.set(1, temp);
            vertices.set(2, convertedPoints.get(3));

        } else if (convertedPoints.get(2).getX() > convertedPoints.get(3).getX()){
            TwoDPoint temp = convertedPoints.get(3);
            vertices.set(1, temp);
            vertices.set(2, convertedPoints.get(2));
        }

//        for (int i = 0; i < 4; i++)
//            vertices.add(finalPoints.get(i));
            // TODO
    }

    public List<TwoDPoint> orderByRows(List<TwoDPoint> points){
        Collections.sort(points, new Comparator<TwoDPoint>() {
            @Override
            public int compare(TwoDPoint o1, TwoDPoint o2) {
                if (o1.getY() < o2.getY())
                    return -1;
                if (o1.getY() > o2.getY())
                    return 1;
                return 0;
            }
        });
        return points;
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
        return vertices;
        // TODO
    }

    /**
     * @return the number of sides of this quadrilateral, which is always set to four
     */
    @Override
    public int numSides() {
        return 4;
    }

    /**
     * Checks whether or not a list of vertices forms a valid quadrilateral. The <i>trivial</i> quadrilateral, where all
     * four corner vertices are the same point, is considered to be an invalid quadrilateral.
     *
     * @param vertices the list of vertices to check against, where each vertex is a <code>Point</code> type.
     * @return <code>true</code> if <code>vertices</code> is a valid collection of points for a quadrilateral, and
     * <code>false</code> otherwise. For example, if three of the four vertices are in a straight line is invalid.
     */
    @Override
    public boolean isMember(List<? extends Point> vertices) {
        List<TwoDPoint> points = (List<TwoDPoint>) vertices;
        Quadrilateral quad = new Quadrilateral(points);
        if (quad.area() == 0)
            return false;
        else
            return true; // TODO
    }

    /**
     * This method snaps each vertex of this quadrilateral to its nearest integer-valued x-y coordinate. For example, if
     * a corner is at (0.8, -0.1), it will be snapped to (1,0). The resultant quadrilateral will thus have all four
     * vertices in positions with integer x and y values. If the snapping procedure described above results in this
     * quadrilateral becoming invalid (e.g., all four corners collapse to a single point), then it is left unchanged.
     * Snapping is an in-place procedure, and the current instance is modified.
     */
    public void snap() {
        List <TwoDPoint> points = vertices;
        Quadrilateral quad = new Quadrilateral(points);

        for (int i = 0; i < 4; i++){
            points.get(i).setX(Math.round(points.get(i).getX()));
            points.get(i).setY(Math.round(points.get(i).getY()));
        }
        if (!quad.isMember(points))
            quad = new Quadrilateral(vertices);
        // TODO
    }

    /**
     * @return the area of this quadrilateral
     */
    public double area() {
        List <TwoDPoint> points = vertices.stream()
                .collect( Collectors.toList());
        List<TwoDPoint> t1 = new ArrayList<>();
        List<TwoDPoint> t2 = new ArrayList<>();

        t1.add(points.get(0));
        t1.add(points.get(1));
        t1.add(points.get(2));

        t2.add(points.get(0));
        t2.add(points.get(2));
        t2.add(points.get(3));

        for (int i = 0; i < points.size(); i++)
            for (int j = points.size() - 1; j > i + 1; j--)
                if (points.get(i).getX() == points.get(j).getX() && points.get(i).getY() == points.get(j).getY())
                    return 0;

        Triangle triangle1 = new Triangle(t1);
        Triangle triangle2 = new Triangle(t2);

        return Math.abs(triangle1.area() + triangle2.area()); // TODO
    }

    /**
     * @return the perimeter (i.e., the total length of the boundary) of this quadrilateral
     */
    public double perimeter() {
        double sum = 0;
        double side1 = Math.sqrt(Math.pow((vertices.get(1).getX() - vertices.get(0).getX()), 2) +
                Math.pow((vertices.get(1).getY() - vertices.get(0).getY()), 2));
        double side2 = Math.sqrt(Math.pow((vertices.get(2).getX() - vertices.get(1).getX()), 2) +
                Math.pow((vertices.get(2).getY() - vertices.get(1).getY()), 2));
        double side3 = Math.sqrt(Math.pow((vertices.get(3).getX() - vertices.get(2).getX()), 2) +
                Math.pow((vertices.get(3).getY() - vertices.get(2).getY()), 2));
        double side4 = Math.sqrt(Math.pow((vertices.get(3).getX() - vertices.get(0).getX()), 2) +
                Math.pow((vertices.get(3).getY() - vertices.get(0).getY()), 2));
        sum = side1 + side2 + side3 + side4;
        return Math.abs(sum); // TODO
    }

    public String toString(){
        if (!isMember(vertices))
            throw new IllegalArgumentException("Not a valid Quadrilateral");

        String quad = "Quadrilateral[";
        for (int i = 0; i < 4; i++){
            if (i == 3){
                quad += "(" + vertices.get(i).getX() + ", " + vertices.get(i).getY() + ")]";
                continue;
            }
            quad += "(" + vertices.get(i).getX() + ", " + vertices.get(i).getY() + "), ";
        }
        return quad;
    }
}
