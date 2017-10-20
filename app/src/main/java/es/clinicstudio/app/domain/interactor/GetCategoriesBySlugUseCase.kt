package es.clinicstudio.app.domain.interactor

import es.clinicstudio.app.data.source.CategoryRepository
import es.clinicstudio.app.domain.entity.Category
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Use case for getting a category by its slug.
 *
 * @author vh @ recursividad.es
 */
class GetCategoriesBySlugUseCase @Inject constructor(
        private val categoryRepository: CategoryRepository)
    :
        UseCase<Category, GetCategoriesBySlugUseCase.Params>() {

    data class Params(
            val slug: String
    )

    /**
     * Create a new observable to obtain the category that matches the slug.
     *
     * @param[params] Parameters to apply when executing the use case.
     */
    override fun buildObservable(params: Params?): Observable<Category> {
        return Observable
                .create<Category> {
                    val posts = getCategoryBySlug(params?.slug ?: "")
                    if (posts != null) {
                        it.onNext(posts)
                    }

                    it.onComplete()
                }
    }

    /**
     * Get a category by its slug.
     */
    private fun getCategoryBySlug(slug: String): Category? {
        return categoryRepository.getCategoriesBySlug(slug)?.get(0)
    }
}