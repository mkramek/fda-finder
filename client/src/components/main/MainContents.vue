<template>
  <FDAForm @results="handleSearchResults" @error="handleError" />
  <FDAResultsTable :results="searchResults" />
  <TotalCount v-if="searchResults" :count="searchResults.products.length" />
</template>

<script setup lang="ts">
import FDAForm from "../form/FDAForm.vue";
import { ref } from "vue";
import { useToast } from "vue-toastification";
import FDAError from "../../helpers/FDAError";
import FDAResponse from "../../helpers/FDAResponse";
import FDAResultsTable from "../FDAResultsTable.vue";
import TotalCount from "../TotalCount.vue";

const toast = useToast();
const searchResults = ref<FDAResponse>();

const handleSearchResults = (results: FDAResponse) => {
  toast.success(`Products fetched. Total count: ${results.products.length}`);
  searchResults.value = results;
}

const handleError = (error: FDAError) => {
  toast.error(error.message);
}
</script>