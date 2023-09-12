package com.linecorp.kotlinjdsl.render.jpql.serializer.impl

import com.linecorp.kotlinjdsl.Internal
import com.linecorp.kotlinjdsl.querymodel.jpql.expression.impl.JpqlCaseWhen
import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlRenderSerializer
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlSerializer
import com.linecorp.kotlinjdsl.render.jpql.writer.JpqlWriter
import kotlin.reflect.KClass

@Internal
class JpqlCaseWhenSerializer : JpqlSerializer<JpqlCaseWhen<*>> {
    override fun handledType(): KClass<JpqlCaseWhen<*>> {
        return JpqlCaseWhen::class
    }

    override fun serialize(part: JpqlCaseWhen<*>, writer: JpqlWriter, context: RenderContext) {
        val delegate = context.getValue(JpqlRenderSerializer)

        writer.write("CASE")
        writer.write(" ")
        writer.writeEach(part.whens.entries, separator = " ") {
            writer.write("WHEN")
            writer.write(" ")
            delegate.serialize(it.key, writer, context)
            writer.write(" ")
            writer.write("THEN")
            writer.write(" ")
            delegate.serialize(it.value, writer, context)
        }

        part.`else`?.let {
            writer.write(" ")
            writer.write("ELSE")
            writer.write(" ")
            delegate.serialize(it, writer, context)
        }

        writer.write(" ")
        writer.write("END")
    }
}
