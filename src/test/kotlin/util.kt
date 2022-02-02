import java.io.File

fun getTestResource(
    filePath: String,
    lineTrim: CharArray = charArrayOf(' ', '\n')
): List<String> {
    val resource = mutableListOf<String>()
    File(filePath).useLines { lines ->
        lines.forEach { line ->
            resource.add(line.trim(*lineTrim))
        }
    }
    return resource
}