
<template>
  <main :class="{ dark: store.state.dark }" class="w-full min-h-screen">
    <div class="bg-stone-100 dark:bg-neutral-700 w-full h-full min-h-screen">
      <MainHeader />
      <MainContents />
      <MainFooter />
    </div>
  </main>
</template>

<script setup lang="ts">
import MainHeader from "./components/main/MainHeader.vue";
import MainContents from "./components/main/MainContents.vue";
import MainFooter from "./components/main/MainFooter.vue";
import { onBeforeMount } from "vue";
import { store } from "./store";

onBeforeMount(() => {
  store.commit('init');
});
</script>
