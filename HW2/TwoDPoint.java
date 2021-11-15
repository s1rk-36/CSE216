import java.sql.Array;
import java.util.*;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point {

    private double x;
    private double y;

    public TwoDPoint(double x, double y) {
        this.x = x;
        this.y = y;
        // TODO
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x1){
        this.x = x1;
    }
    public void setY(double y1){
        this.y = y1;
    }
    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{x, y}; // TODO
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double... coordinates) throws IllegalArgumentException {
        if (coordinates.length % 2 != 0)
            throw new IllegalArgumentException();


        List<TwoDPoint> list = new ArrayList<>();
        for (int i = 0; i <= (coordinates.length / 2) + 2; i += 2) {

            list.add(new TwoDPoint(coordinates[i], coordinates[i + 1]));
        }

        return list; // TODO
    }
}
