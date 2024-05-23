# Develop Real-Time Spring Boot Unit Tests

## Integration   Test vs Unit Test

### Integration Test (集成测试) 

Test multiple components together as part of a test plan

• Determine if software units work together as expected
• Identify any negative side effects due to integration
• Can test using mocks / stubs
• Can also test using live integrations (database, file system)

### Unit Test(单元测试)

Testing an individual unit of code for correctness

• Provide fixed inputs
• Expect known output

## Junit 

### Assertions(断言)

| Method Name     | DESCRIPTION                         |
| :-------------- | ----------------------------------- |
| assertEquals    | Assert that the value are equal     |
| assertNotEquals | Assert that the value are not equal |
| assertNull      | Assert that the value is null       |
| assertNotNull   | Assert that the value is not null   |

### Static Import

```java
import static org.junit.jupiter.api.Assertions.*;
```

```Java
// Assertions.assertEquals(expected, actual, "2 + 4 must be 6");
assertEquals(expected, actual, "2 + 4 must be 6");

```

### Lifecycle Method Annotations

![image-20240522232215621](C:\Users\Wang\AppData\Roaming\Typora\typora-user-images\image-20240522232215621.png)

| Annotation  | DESCRIPTION                         |
| :---------- | ----------------------------------- |
| @BeforeAll  | Assert that the value are equal     |
| @AfterAll   | Assert that the value are not equal |
| @AfterEach  | Assert that the value is null       |
| @BeforeEach | Assert that the value is not null   |

### JUnit Custom Display Names

Provide a more descriptive name for the test

| Annotation   | DESCRIPTION                                                  |
| :----------- | ------------------------------------------------------------ |
| @DisplayName | Custom display name with spaces, special characters and emojis.<br/>Useful for test reports in IDE or external test runner |

#### Display Name Generators

| Name                | DESCRIPTION                                                  |
| :------------------ | ------------------------------------------------------------ |
| Simple              | Removes trailing parentheses from test method name           |
| ReplaceUnderscores  | Replaces underscores in test method name with spaces         |
| IndicativeSentences | Generate sentence based on test class name and test method name |



```java
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class DemoUtilsTest {

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoUtilsTest {

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
class DemoUtilsTest {
```

![image-20240523002522683](C:\Users\Wang\AppData\Roaming\Typora\typora-user-images\image-20240523002522683.png)



### Same and True



| Method Name   | DESCRIPTION                                   |
| :------------ | --------------------------------------------- |
| assertSame    | Assert that items refer to same object        |
| assertNotSame | Assert that items do not refer to same object |

```java
assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to same object");
```

| Method Name | DESCRIPTION                    |
| :---------- | ------------------------------ |
| assertTrue  | Assert that condition is true  |
| assertFalse | Assert that condition is false |

```java
assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "This should return true");
```

### Arrays, Iterables and Lines

| Method Name          | DESCRIPTION                                                  |
| :------------------- | ------------------------------------------------------------ |
| assertArrayEquals    | Assert that both object arrays are deeply equal              |
| assertIterableEquals | Assert that both object iterables are deeply equal(An "iterable" is an instance of a class that implements the java.lang.Iterable interface) |
| assertLinesMatch     | Assert that both lists of strings match                      |

```java
assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
```

### Throws and Timeout

| Method Name               | DESCRIPTION                                                  |
| :------------------------ | ------------------------------------------------------------ |
| assertThrows              | Assert that an executable throws an exception of expected type |
| assertDoesNotThrow        | Assert that an executable does not throw an exception of expected type |
| assertTimeoutPreemptively | Assert that an executable completes before given timeout is exceeded |

```
assertTimeoutPreemptively(Duration.ofSeconds(3), () -> { demoUtils.checkTimeout(); },
"Method should execute in 3 seconds");
```

### Running Tests in Order

By default, test classes and methods will be ordered using an algorithm that is deterministic but intentionally nonobvious.

| Annotation       | DESCRIPTION                                              |
| :--------------- | -------------------------------------------------------- |
| @TestMethodOrder | Configures the order/sort algorithm for the test methods |

| Name                          | DESCRIPTION                                                |
| :---------------------------- | ---------------------------------------------------------- |
| MethodOrderer.DisplayName     | Sorts test methods alphanumerically based on display names |
| MethodOrderer.MethodName      | Sorts test methods alphanumerically based on method names  |
| MethodOrderer.Random          | Pseudo-random order based on method names                  |
| MethodOrderer.OrderAnnotation | Sorts test methods numerically based on @Order annotation  |

```java
@TestMethodOrder(MethodOrderer.DisplayName.class)
class DemoUtilsTest {
```

| Annotation | DESCRIPTION                                                  |
| :--------- | ------------------------------------------------------------ |
| @Order     | Manually specify the order with an int number<br/>- Order with lowest number has highest priority<br/>- Negative numbers are allowed |

![image-20240523010758904](Develop Real-Time Spring Boot Unit Tests.assets/image-20240523010758904.png)



### Code Coverage and Test Reports
with IntelliJ

Code Coverage measures how many methods/lines are called by your tests

On most teams, 70%-80% is acceptable

### Conditional Tests

| Name                          | DESCRIPTION                                          |
| :---------------------------- | ---------------------------------------------------- |
| @Disabled                     | Disable a test method                                |
| @EnabledOnOs                  | Enable test when running on a given operating system |
| @EnabledOnJre                 | Enable test for a given Java version                 |
| @EnabledForJreRange           | Enable test for a given Java version range           |
| @EnabledIfSystemProperty      | Enable test based on system property                 |
| @EnabledIfEnvironmentVariable | Enable test based on environment variable            |

```java
@EnabledOnJre(JRE.JAVA_13)
@EnabledForJreRange(min=JRE.JAVA_13, max=JRE.JAVA_18)
@EnabledIfSystemProperty(named="LUV2CODE_SYS_PROP", matches="CI_CD_DEPLOY")
@EnabledIfEnvironmentVariable(named="LUV2CODE_ENV", matches="DEV")
```

![image-20240523012247512](Develop Real-Time Spring Boot Unit Tests.assets/image-20240523012247512.png)

## TDD

![image-20240523012402889](Develop Real-Time Spring Boot Unit Tests.assets/image-20240523012402889.png)

### 

| Name           | DESCRIPTION                                         |
| :------------- | --------------------------------------------------- |
| @ValueSource   | Array of values: Strings, ints, doubles, floats etc |
| @CsvSource     | Array of CSV String values                          |
| @CsvFileSource | CSV values read from a file                         |
| @EnumSource    | Enum constant values                                |
| @MethodSource  | Custom method for providing values                  |

![image-20240523013720096](Develop Real-Time Spring Boot Unit Tests.assets/image-20240523013720096.png)

![image-20240523013835936](Develop Real-Time Spring Boot Unit Tests.assets/image-20240523013835936.png)

```java
@ParameterizedTest
@MethodSource("provideStringsForIsBlank")
void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
    assertEquals(expected, Strings.isBlank(input));
}

private static Stream<Arguments> provideStringsForIsBlank() {
    return Stream.of(
      Arguments.of(null, true),
      Arguments.of("", true),
      Arguments.of("  ", true),
      Arguments.of("not blank", false)
    );
}
```

