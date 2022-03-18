package tutor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleTest {
    @DisplayName(value="三角形一般边界测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形一般边界测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest(int a, int b,int c,String expected) {
        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形健壮测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形健壮测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest2(int a, int b,int c,String expected) {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }@DisplayName(value="三角形最坏情况一般边界测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形最坏情况一般边界测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest3(int a, int b,int c,String expected) {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形最坏情况健壮测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形最坏情况健壮测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest4(int a, int b,int c,String expected) {

        Triangle triangle=new Triangle();

        String type = triangle.classify(a, b, c);

        assertEquals(expected, type);
    }
}