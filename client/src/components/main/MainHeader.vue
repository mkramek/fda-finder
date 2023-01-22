<template>
  <div class="w-full h-24 bg-stone-300 dark:bg-neutral-800 flex items-center justify-between px-6">
    <h1 class="text-3xl dark:text-white">{{ strings.app }}</h1>
    <ThemeSwitch />
  </div>
</template>

<script setup lang="ts">
import strings from "../../assets/strings.json";
import ThemeSwitch from "../ThemeSwitch.vue";
</script>