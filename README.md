# Jei
Jei is a Java-Framework. It wants to make everything about the language more simple and understandable.

## Installation & Requirements
Download the master-branch and compress it into a JAR-File. 
It requires JRE 1.8 or greater.

## Basics
When using Jei, you will use almost no types that come with the standard library.
Instead, their replacements provided by the framework will be used.

### `jei.Base`
This class is supposed to be used as base for every new class. Forget about `java.lang.Object`, this is the new standard.
Every type in Jei extends this class by default. All examples provided in this readme or elsewhere in this repo will assume that you have extended this class.

#### `jei.$`
If you are unable to extend from this class, or just don't want to do it, you can use the class `jei.$`.
It contains all pseudo-globals provided by `Base`, but as `public static`-methods.  
Use either a static import:
```java
import static jei.$; 
```
or use the class directly:
```
jei.$.print(...)
```

### `jei.collections`
This package contains the frameworks collections. It provides replacements for the basic types of the Java Collections Framework.
> this package is a heavy WIP and nowhere near final

| Type          | Replacement For                         | Creation                                                 |
|:-------------:|:----------------------------------------|:---------------------------------------------------------|
| `Array<T>`    | `java.util.List<T>` and default arrays. | `Array<String> array = array("Put", "Entries", "Here");` |
| `Table<K, V>` | `java.util.Map<K, V>`                   | `Table<Integer, String> array = table(__(1, "value1"), __(2, "value2");`     |
| `Set<T>`      | `java.util.Set<K, V>`                   | `Set<String> set = Set.of("Put", "Entries", "Here");`    |

### `jei.simple.Option`
Jei aims to eliminate `null`s. To do so, it uses a similar approach to Scala, Kotlin and Swift and uses an optional type.
Since Java 1.8, there exists the type `java.util.Optional<T>`. However, the Jei-Alternative `Option` is integrated more neatly into the framework, and is the preffered way to deal with `null`.

__no value__<br>
`none();`

__some value__<br>
`some(some_value);`

__no or some value__<br>
`wrap(some_or_none);`
