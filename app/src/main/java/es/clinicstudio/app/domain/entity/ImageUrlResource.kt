package es.clinicstudio.app.domain.entity

/**
 * @author vh @ recursividad.es
 */
data class ImageUrlResource(
        private val alternatives: Map<Int, String>
) {

    companion object {
        /**
         * Extracts all the images resources from any HTML content.
         *
         * Each image resource may have one or several alternative URLs for
         * different image sizes.
         *
         * @param[htmlContent] HTML content from which extract the image resources.
         * @return List of image resources found in the content.
         */
        fun from(htmlContent: String): List<ImageUrlResource>? {
            val imageUrlRegex = Regex("""http[^\s]*?\.(jpg|jpeg|png)""")
            val urlImageSizeRegex = Regex("""http[^\s]*?(\d+)x\d+\.(jpg|jpeg|png)""")

            return extractImageSourceSets(htmlContent)
                    ?.map {
                        val map = HashMap<Int, String>()
                        imageUrlRegex.findAll(it)
                                .forEach {
                                    val url = it.value
                                    val width = urlImageSizeRegex.find(url)?.groups?.get(1)?.value?.toInt() ?: Int.MAX_VALUE

                                    map.put(width, url)
                                }

                        ImageUrlResource(map)
                    }
        }

        /**
         * Extracts all the image source sets from any HTML content.
         *
         * @param[htmlContent] HTML content from which extract the image source sets.
         * @return List of image source sets found in the content.
         */
        private fun extractImageSourceSets(htmlContent: String): List<String>? {
            val imageSourceSetRegex = Regex("""<img.*?srcset="(.*?)".*?/>""")

            return if (imageSourceSetRegex.containsMatchIn(htmlContent)) {
                imageSourceSetRegex
                        .findAll(htmlContent)
                        .map { it.value }
                        .toList()
            }
            else {
                null
            }
        }
    }

    /**
     * Get the URL for the smallest alternative size of the image.
     *
     * @return URL of the smallest alternative size of the image.
     */
    fun getSmallest(): String? {
        return if (!alternatives.isEmpty()) {
            alternatives[alternatives.keys.sorted().first()]
        }
        else {
            null
        }
    }

    /**
     * Get the URL for the biggest alternative size of the image.
     *
     * @return URL of the biggest alternative size of the image.
     */
    fun getBiggest(): String? {
        return if (!alternatives.isEmpty()) {
            alternatives[alternatives.keys.sorted().last()]
        }
        else {
            null
        }
    }

    /**
     * Get the URL for the alternative that best fits the target [width].
     *
     * @param[width] Minimum desired width of the image.
     * @return URL of the alternative size of the image that best fits the specified [width].
     */
    fun getBestFit(width: Int): String? {
        return if (!alternatives.isEmpty()) {
            alternatives[alternatives.keys.sorted().firstOrNull { it >= width } ?: alternatives.keys.sorted().last()]
        }
        else {
            null
        }
    }
}