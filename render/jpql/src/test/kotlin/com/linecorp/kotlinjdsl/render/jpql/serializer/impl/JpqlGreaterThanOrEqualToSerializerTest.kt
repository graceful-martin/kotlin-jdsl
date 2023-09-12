package com.linecorp.kotlinjdsl.render.jpql.serializer.impl

import com.linecorp.kotlinjdsl.querymodel.jpql.expression.Expressions
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.Predicates
import com.linecorp.kotlinjdsl.querymodel.jpql.predicate.impl.JpqlGreaterThanOrEqualTo
import com.linecorp.kotlinjdsl.render.TestRenderContext
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlRenderSerializer
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlSerializerTest
import com.linecorp.kotlinjdsl.render.jpql.writer.JpqlWriter
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test

@JpqlSerializerTest
class JpqlGreaterThanOrEqualToSerializerTest : WithAssertions {
    private val sut = JpqlGreaterThanOrEqualToSerializer()

    @MockK
    private lateinit var writer: JpqlWriter

    @MockK
    private lateinit var serializer: JpqlRenderSerializer

    private val expression1 = Expressions.value(1)
    private val expression2 = Expressions.value(2)

    @Test
    fun handledType() {
        // when
        val actual = sut.handledType()

        // then
        assertThat(actual).isEqualTo(JpqlGreaterThanOrEqualTo::class)
    }

    @Test
    fun serialize() {
        // given
        val part = Predicates.greaterThanOrEqualTo(
            expression1,
            expression2,
        )
        val context = TestRenderContext(serializer)

        // when
        sut.serialize(part as JpqlGreaterThanOrEqualTo<*>, writer, context)

        // then
        verifySequence {
            serializer.serialize(expression1, writer, context)
            writer.write(" ")
            writer.write(">=")
            writer.write(" ")
            serializer.serialize(expression2, writer, context)
        }
    }
}
