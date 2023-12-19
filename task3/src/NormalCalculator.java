import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalCalculator {
    @Test
    public void testCalculateNormals() {
        ArrayList<Point> testVertices = new ArrayList<>();
        ArrayList<Integer> testTriangleIndices = new ArrayList<>();
        // Добавляем тестовые вершины и индексы треугольников
        testVertices.add(new Point(4, 0, 0)); // Вершина 0
        testVertices.add(new Point(1, 0, 0)); // Вершина 1
        testVertices.add(new Point(0, 1, 0)); // Вершина 2
        testTriangleIndices.add(0); // Вершина 0 для треугольника 1
        testTriangleIndices.add(1); // Вершина 1 для треугольника 1
        testTriangleIndices.add(2); // Вершина 2 для треугольника 1

        ArrayList<Point> normals = calculateNormals(testVertices, testTriangleIndices);

        assertEquals(0.0, normals.get(0).x, 0.0001);
        assertEquals(0.0, normals.get(0).y, 0.0001);
        assertEquals(-1.0, normals.get(0).z, 0.0001);
        System.out.println("Normal: (" + normals.get(0).x + ", " + normals.get(0).y + ", " + normals.get(0).z + ")");
    }

    public static void main(String[] args) {

        ArrayList<Point> vertices = new ArrayList<>();
        ArrayList<Integer> triangleIndices = new ArrayList<>();

// Добавляем вершины с целыми координатами
        vertices.add(new Point(4, 0, 0));   // Вершина 0
        vertices.add(new Point(1, 0, 0));   // Вершина 1
        vertices.add(new Point(0, 1, 0));   // Вершина 2
        vertices.add(new Point(1, 1, 0));   // Вершина 3
        vertices.add(new Point(0, 0, 0));   // Вершина 4
        vertices.add(new Point(0, 3, 0));   // Вершина 5

// Добавляем индексы треугольников
        triangleIndices.add(0); // Вершина 0 для треугольника 1
        triangleIndices.add(1); // Вершина 1 для треугольника 1
        triangleIndices.add(2); // Вершина 2 для треугольника 1

        triangleIndices.add(2); // Вершина 2 для треугольника 2
        triangleIndices.add(1); // Вершина 1 для треугольника 2
        triangleIndices.add(3); // Вершина 3 для треугольника 2

        triangleIndices.add(0); // Вершина 0 для треугольника 3
        triangleIndices.add(4); // Вершина 4 для треугольника 3
        triangleIndices.add(5); // Вершина 5 для треугольника 3


        ArrayList<Point> normals = calculateNormals(vertices, triangleIndices);
        for (Point normal : normals) {
            System.out.println("Normal: (" + normal.x + ", " + normal.y + ", " + normal.z + ")");
        }
    }

    public static ArrayList<Point> calculateNormals(ArrayList<Point> vertices, ArrayList<Integer> triangleIndices) {
        ArrayList<Point> normals = new ArrayList<>();

        // инициализируем нормали нулевыми векторами
        for (int i = 0; i < vertices.size(); i++) {
            normals.add(new Point(0, 0, 0));
        }

        // вычисление нормалей
        for (int i = 0; i < triangleIndices.size(); i += 3) {
            int i1 = triangleIndices.get(i);
            int i2 = triangleIndices.get(i + 1);
            int i3 = triangleIndices.get(i + 2);

            Point v1 = vertices.get(i1);
            Point v2 = vertices.get(i2);
            Point v3 = vertices.get(i3);

            Point edge1 = new Point(v2.x, v2.y, v2.z);
            edge1.sub(v1);

            Point edge2 = new Point(v3.x, v3.y, v3.z);
            edge2.sub(v1);

            Point normal = new Point(0, 0, 0);
            normal.cross(edge1, edge2);

            // Добавляем нормали к соответствующим вершинам
            //исправить добавить проверочки
            normals.get(i1).x += normal.x;
            normals.get(i1).y += normal.y;
            normals.get(i1).z += normal.z;

            normals.get(i2).x += normal.x;
            normals.get(i2).y += normal.y;
            normals.get(i2).z += normal.z;

            normals.get(i3).x += normal.x;
            normals.get(i3).y += normal.y;
            normals.get(i3).z += normal.z;
        }

        // Нормализуем нормали
        for (Point normal : normals) {
            normal.normalize();
        }

        return normals;
    }
}
