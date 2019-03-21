package com.motiion.customer.lowes

import org.apache.daffodil.japi.Daffodil
import org.apache.daffodil.japi.infoset.JDOMInfosetOutputter
import org.apache.daffodil.japi.io.InputSourceDataInputStream
import org.jdom2.output.Format
import org.jdom2.output.XMLOutputter
import java.io.File

fun main(args: Array<String>) {

    val compiler = Daffodil.compiler()

    val pf = compiler.compileFile(File(("/Users/dag/devel/motiion/customers/customer-lowes/src/main/resources/TlogSA.xsd")))

    if (pf.isError()) {
        // didn't compile schema. Must be diagnostic of some sort.
        val diags = pf.getDiagnostics()
        for (d in diags) {
            System.err.println(d.getSomeMessage())
        }
        System.exit(1)
    }
    val dp = pf.onPath("/")
    if (dp.isError()) {
        // didn't compile schema. Must be diagnostic of some sort.
        val diags = dp.getDiagnostics()
        for (d in diags) {
            System.err.println(d.getSomeMessage())
        }
        System.exit(1)
    }
    //
    // Parse - parse data to XML
    //
    println("**** Parsing data into XML *****")
//    val file = File("/Users/dag/devel/3p/IBM4690-TLOG/SA/TestData_SA/EAMTRANA_2.DAT")
    val file = File("/Users/dag/Downloads/tlog")
    val fis = java.io.FileInputStream(file)
    val dis = InputSourceDataInputStream(fis)
    //
    // Setup JDOM outputter
    //
    val outputter = JDOMInfosetOutputter()

    val res = dp.parse(dis, outputter)

    // Check for errors
    //
    val err = res.isError
    if (err) {
        // didn't parse the data. Must be diagnostic of some sort.
        val diags = res.diagnostics
        for (d in diags) {
            System.err.println(d.someMessage)
        }
        System.exit(2)
    }

    val doc = outputter.result
    println(doc)

    val xmlOutputter = XMLOutputter(Format.getPrettyFormat())
    xmlOutputter.output(doc, System.out)
}
