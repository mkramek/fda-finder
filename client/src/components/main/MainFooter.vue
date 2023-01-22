<template>
  <div class="w-full h-24 mt-12 bg-stone-300 dark:bg-neutral-800 dark:text-white flex items-center justify-end px-6">
    <span class="text-right">&copy; {{ strings.app }}</span>
  </div>
</template>

<script setup lang="ts">
import strings from "../../assets/strings.json";
</script>