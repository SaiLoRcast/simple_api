package com.polygalov.springrest.sr.model

import javax.validation.constraints.NotBlank

class NewEmployeeForm(
        @get: NotBlank val titleName: String,
        @get: NotBlank val editableTextName: String,
        @get: NotBlank val titlePhone: String,
        @get: NotBlank val editableTextPhone: String
)