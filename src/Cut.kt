
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import java.io.*
import kotlin.system.exitProcess


class Cut(
    private val iName: String?,
    private val oName: String?,
    private val symOrWord: Boolean,
    private val range: String
) {


    private fun valRange(range: String): List<Int> {
        val listR = mutableListOf<Int>()
        val parts: List<String> = range.split("-")
        val k: Int = parts[1].toInt()
        val n: Int = parts[0].toInt()
        if (k <= n) {
            throw IllegalArgumentException(
                "Invalid Range." +
                        "The first number must be less than the second." +
                        "Rewrite the command correctly please."
            )
        }
        if (k > n ) {
            listR.add(n)
            listR.add(k)
        }
        return listR
    }



    private fun cutPart(line: String, symOrWord: Boolean, valRange: List<Int>): String {
        val line1 = line.trim()
        var string = ""
        if (!symOrWord) {
            val lastIndex: Int = line1.lastIndex
            return when {
                valRange(range)[1] < line1.length -> line1.substring(valRange[0], valRange[1])
                else -> line1.substring(valRange[0], lastIndex)
            }

        } else {
            val wordList: List<String> = line1.split(" ")
            val builder = StringBuilder()
            for (i in valRange[0] until wordList.size)
                if (i <= valRange[1]) {
                    builder.append(wordList[i])
                    builder.append(' ')
                    string= builder.toString().trim()
                }
        }
        return string
    }


    private fun writer(result: String) {
        try {
            if (oName != null ){
                    File(oName).writeText(result)
            }

        } catch (e: IOException) {
            throw IllegalArgumentException(e.message)
        }
    }

    fun cutInfo() {
        val list: MutableList<String>
        val br: BufferedReader?
        try {
            br = if (iName != null) {
                val file = File(iName)
                BufferedReader(InputStreamReader(FileInputStream(file)))
            } else BufferedReader (InputStreamReader (System.`in`))

                list = br.readLines().toMutableList()
            /**
             * ecrire ctrl + D quand tu rune le programme
             */

        }catch (e: IOException) {
            throw IllegalArgumentException(
                "Error in the receveid file"
                        + " Rewrite the command correctly please."
            )
        }

        val valRange: List<Int> = valRange(range)
        val sBuilder = StringBuilder()
        var separ = ""

        for (i in list) {
            sBuilder.append(separ)
            separ = "\n"
            sBuilder.append(cutPart(i,symOrWord,valRange))
        }
        if (oName == null) println(sBuilder.toString())
        else writer(sBuilder.toString())

    }

    companion object{

        @JvmStatic
        fun main(args: Array<String>) {
            val symbolC = Option("c",false,"cutBySymbol" )
            symbolC.isRequired = false
            val wordW = Option("w", false, "cutByWord")
            wordW.isRequired = false
            val oFile = Option("o", false, "outFile")
            oFile.isRequired = false


            val options = Options()
            options.addOption(symbolC)
            options.addOption(wordW)
            options.addOption(oFile)

            val clParser = DefaultParser()
            val commandLine: CommandLine

            try {
                commandLine = clParser.parse(options, args)
                val cl = commandLine.argList
                var ofile: String? = null
                var ifile: String? = null
                val valRange: String
                if (commandLine.hasOption("-o")){
                    ofile  = cl[0]
                    if (cl.size == 3) {
                        ifile = cl[1]
                        valRange = cl[2]
                    } else valRange = cl[1]
                }
                else {
                    if (cl.size == 2) {
                        ifile = cl[0]
                        valRange = cl[1]
                    }
                    else valRange = cl[0]
                }
                val symOrWord: Boolean =
                    if (commandLine.hasOption("-w") && commandLine.hasOption("-c"))
                        throw IllegalArgumentException()
                    else if(commandLine.hasOption("-c")) false
                    else if (commandLine.hasOption("-w")) true else throw IllegalArgumentException()

                val cut = Cut(ifile, ofile, symOrWord, valRange)
                cut.cutInfo()


            } catch (e: Exception) {
                System.err.println(e.message)
                exitProcess(1)
            }
        }
    }
}