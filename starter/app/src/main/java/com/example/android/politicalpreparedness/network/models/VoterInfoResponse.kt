package com.example.android.politicalpreparedness.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VoterInfoResponse(
    val election: Election,
//    val pollingLocations: String? = null, //TODO: Future Use
//    val contests: String? = null, //TODO: Future Use
    val state: List<State>? = null,
//    val electionElectionOfficials: List<ElectionOfficial>? = null
)

data class VoteInfo(val election: Election, val state: List<State>? = null)

fun VoterInfoResponse.asVoteInfo(): VoteInfo {
    return VoteInfo(this.election, this.state)
}
