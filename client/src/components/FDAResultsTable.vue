<template>
  <table v-if="!!results" class="mx-auto mt-11 w-[85vw] dark:text-white border-collapse border border-black">
    <thead class="border border-black bg-stone-300">
    <tr>
      <th class="border border-stone-500 bg-stone-300 dark:bg-neutral-600 dark:border-neutral-900 text-lg py-3">{{ strings.table.manufacturer }}</th>
      <th class="border border-stone-500 bg-stone-300 dark:bg-neutral-600 dark:border-neutral-900 text-lg py-3">{{ strings.table.brand }}</th>
      <th class="border border-stone-500 bg-stone-300 dark:bg-neutral-600 dark:border-neutral-900 text-lg py-3">{{ strings.table.product }}</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(product, idx) of results.products" :key="idx">
      <td class="border border-stone-300 bg-white dark:bg-neutral-500 dark:border-neutral-700 text-center px-4 py-2">{{ results.manufacturer }}</td>
      <td class="border border-stone-300 bg-white dark:bg-neutral-500 dark:border-neutral-700 text-center px-4 py-2">{{ product.brand_name }}</td>
      <td class="border border-stone-300 bg-white dark:bg-neutral-500 dark:border-neutral-700 text-center px-4 py-2">{{ product.dosage_form.toLocaleLowerCase() }} ({{ product.marketing_status.toLocaleLowerCase() }})</td>
    </tr>
    </tbody>
  </table>
</template>

<script setup lang="ts">
import strings from "../assets/strings.json";
import FDAResponse from "../helpers/FDAResponse";
import { PropType } from "vue";

defineProps({
  results: Object as PropType<FDAResponse>
});
</script>
