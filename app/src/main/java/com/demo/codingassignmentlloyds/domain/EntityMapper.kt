package com.demo.codingassignmentlloyds.domain

// Helper class used to map Network model to domain model
interface EntityMapper<NetworkModel, DomainModel> {
    fun transform(entity: NetworkModel): DomainModel
}