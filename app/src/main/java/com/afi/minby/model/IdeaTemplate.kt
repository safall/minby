package com.afi.minby.model

import com.afi.minby.sendidea.IdeaTemplate
import javax.inject.Inject

class IdeaTemplateImpl @Inject constructor() {
    var ideaTemplate = IdeaTemplate()

    fun clear() {
        ideaTemplate = IdeaTemplate()
    }

    fun update(updatedTemplate: IdeaTemplate) {
        ideaTemplate = updatedTemplate
    }
}
