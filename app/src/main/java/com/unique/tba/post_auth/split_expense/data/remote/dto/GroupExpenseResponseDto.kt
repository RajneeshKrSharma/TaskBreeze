package com.unique.tba.post_auth.split_expense.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GroupExpenseResponseDto(
    @SerializedName("data")
    val `data`: List<Data?>?
) {
    data class Data(
        @SerializedName("collaborators")
        val collaborators: List<Collaborator?>?,
        @SerializedName("created_by_google_auth_user")
        val createdByGoogleAuthUser: Any?,
        @SerializedName("created_by_user")
        val createdByUser: Int?,
        @SerializedName("grp_name")
        val grpName: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("last_settled_amt")
        val lastSettledAmt: String?,
        @SerializedName("last_settled_date_time")
        val lastSettledDateTime: Any?,
        @SerializedName("t_amt")
        val tAmt: String?,
        @SerializedName("t_item")
        val tItem: Int?
    ) {
        data class Collaborator(
            @SerializedName("collab_google_auth_user")
            val collabGoogleAuthUser: Any?,
            @SerializedName("collab_user")
            val collabUser: Int?,
            @SerializedName("expenses")
            val expenses: List<Expense?>?,
            @SerializedName("group_expense_id")
            val groupExpenseId: Int?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("redirect_upi_url")
            val redirectUpiUrl: Any?,
            @SerializedName("requested_payment_qr_url")
            val requestedPaymentQrUrl: Any?,
            @SerializedName("settle_medium")
            val settleMedium: Any?,
            @SerializedName("settle_mode")
            val settleMode: Any?,
            @SerializedName("status")
            val status: Any?
        ) {
            data class Expense(
                @SerializedName("date_time")
                val dateTime: String?,
                @SerializedName("i_amt")
                val iAmt: String?,
                @SerializedName("i_desp")
                val iDesp: String?,
                @SerializedName("i_name")
                val iName: String?,
                @SerializedName("i_notes")
                val iNotes: String?,
                @SerializedName("i_qty")
                val iQty: String?,
                @SerializedName("id")
                val id: Int?,
                @SerializedName("is_settled")
                val isSettled: Boolean?
            )
        }
    }

    companion object {
        fun empty() = GroupExpenseResponseDto(data = emptyList())
    }
}