<template>
  <form @submit.prevent="handleSubmit" class="flex flex-wrap md:flex-nowrap justify-center items-end gap-6 m-6 dark:text-white">
    <TextInput :label="strings.label.manufacturer" required v-model="manufacturer" />
    <TextInput :label="strings.label.brand" v-model="brand" />
    <button :disabled="!!loading" class="w-28 border border-stone-400 px-3 py-2 rounded-md text-white text-lg bg-stone-400 dark:bg-neutral-500 dark:hover:bg-neutral-400 hover:bg-stone-500" type="submit">
      <Spinner v-if="loading" />
      <span v-else>{{ strings.label.search }}</span>
    </button>
  </form>
</template>

<script setup lang="ts">
import strings from "../../assets/strings.json";
import { ref } from "vue";
import axios, { AxiosError, AxiosResponse } from "axios";
import TextInput from "./TextInput.vue";
import FDAResponse from "../../helpers/FDAResponse";
import FDAError from "../../helpers/FDAError";
import Spinner from "../Spinner.vue";

const emit = defineEmits(['results', 'error']);

const manufacturer = ref<string>();
const brand = ref<string>();
const loading = ref<boolean>(false);

const handleSubmit = () => {
  loading.value = true;
  axios.get('http://localhost:8080/api/v1/drugs', {
    params: {
      manufacturer: manufacturer.value,
      brand: brand.value
    }
  }).then((response: AxiosResponse<FDAResponse>) => {
    emit('results', response.data);
  }).catch((error: AxiosError<FDAError>) => {
    emit('error', error.response?.data);
  }).finally(() => {
    loading.value = false;
  });
}
</script>
