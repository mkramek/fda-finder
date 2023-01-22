<template>
  <div class="w-[85vw] mx-auto text-right mt-2 dark:text-white">{{ strings.summary }}: {{ count }}</div>
</template>

<script setup lang="ts">
import strings from '../assets/strings.json';

defineProps({
  count: Number
});
</script>