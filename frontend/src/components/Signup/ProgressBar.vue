<template>
  <div>
    <slot name="bar">
      <b-progress 
        class="mt-5"
        value="22" 
        height="2rem"
        max="100" 
        show-progress 
        animated
        style="
          background-color: white;
          border-radius: 10px / 10px;
          box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.16);
        "
      ></b-progress>
    </slot>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>