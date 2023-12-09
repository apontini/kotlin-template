import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import org.koin.core.component.getScopeName
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.test.KoinTest

abstract class UnitTest : KoinTest, DescribeSpec() {
    abstract fun koinModule() : Module

    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

    override suspend fun beforeSpec(spec: Spec) {
        startKoin {
            modules(koinModule())
        }
    }

    override suspend fun afterSpec(spec: Spec) {
        stopKoin()
    }

    override suspend fun beforeAny(testCase: TestCase) {
        print("  ".repeat(findDepth(testCase)))
        println(testCase.displayName)
    }

    private fun findDepth(testCase: TestCase?) : Int {
        if(testCase?.parent == null) {
            return 0
        }
        return findDepth(testCase.parent) + 1
    }
}