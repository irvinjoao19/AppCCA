package com.dsige.cca.annotation

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class StatusKey {
    companion object {
        const val VIDEO = 1
        const val PHOTO = 2
    }
}
